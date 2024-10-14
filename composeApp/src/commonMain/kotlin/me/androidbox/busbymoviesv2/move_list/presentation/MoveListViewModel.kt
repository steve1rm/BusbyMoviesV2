package me.androidbox.busbymoviesv2.move_list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import app.cash.paging.map
import co.touchlab.kermit.Logger
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.core.data.network.Routes
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.presentation.utils.mapImageSize
import me.androidbox.busbymoviesv2.move_list.data.repository.imp.MovieListPagingRepositoryImp
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListUseCase
import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieResult

class MoveListViewModel(
    private val movieListUseCase: MovieListUseCase,
    private val configurationUseCase: ConfigurationUseCase,
    private val movieListPagingRepositoryImp: MovieListPagingRepositoryImp
) : ViewModel() {

    private val _movieListFlow = MutableStateFlow<PagingData<MovieResult>>(PagingData.empty())
    val movieListFlow = _movieListFlow.asStateFlow()

    var movieListState by mutableStateOf(MovieListState())
        private set

    init {
   //     movieList(Routes.NOW_PLAYING)
        viewModelScope.launch {
        val configuration = viewModelScope.async {
            configurationUseCase.execute()
        }
        val configurationModel = configuration.await()
            val imageSize = configurationModel?.let {
                mapImageSize(it)
            } ?: "original"

            movieListPagingRepositoryImp.movieListPaging(Routes.NOW_PLAYING)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                       _movieListFlow.value = pagingData.map {
                           MovieResult(
                               id = it.id,
                               title = it.title,
                               overview = it.overview,
                               posterPath = it.posterPath.toPosterWithImageSize(imageSize),
                               backdropPath = it.backdropPath,
                               voteAverage = it.voteAverage,
                               releaseDate = it.releaseDate
                           )
                       }
                   }
                }
        }


    fun onMovieListAction(action: MovieListAction) {
        when(action) {
            is MovieListAction.OnMovieClicked -> {
                /** Navigate to movie details screen */
                Logger.d {
                    "Movie Item clicked ${action.movieId}"
                }
            }
            is MovieListAction.OnMovieListNavigationItemClicked -> {
                /** Send request to get the movie list of different categories */
                Logger.d {
                    "Movie navigation item clicked ${action.movieCategory.name}"
                }

                movieList(action.movieCategory.movieRoute)
            }
        }
    }

    private var job: Job? = Job()

    private fun movieList(movieRoute: String) {

        Logger.d {
            "Job Status before ${job?.isActive}"
        }

        job = viewModelScope.launch {
            try {
                movieListState = movieListState.copy(
                    isLoading = true
                )

                val configuration = viewModelScope.async {
                    configurationUseCase.execute()
                }

                val movieList = viewModelScope.async {
                    movieListUseCase.execute(1, movieRoute)
                    //   movieListRemoteDataSource.movieList(Routes.NOW_PLAYING)
                }

                val configurationModel = configuration.await()
                val movieListResult = movieList.await()

                when (movieListResult) {
                    is CheckResult.Failure -> {
                        Logger.e { "Fetch from the movie endpoint ${movieListResult.exceptionError}" }

                        movieListState = movieListState.copy(
                            isLoading = false
                        )
                    }

                    is CheckResult.Success -> {
                        Logger.d { "Success movie list ${movieListResult.data}" }

                        val imageSize = configurationModel?.let {
                            mapImageSize(it)
                        } ?: "original"

                        movieListState = movieListState.copy(
                            isLoading = false,
                            movieList = movieListResult.data.toMovieList(imageSize)
                        )
                    }
                }
            }
            catch (ex: Exception) {
                println(ex.message)
                movieListState = movieListState.copy(
                    isLoading = false
                )
            }
        }

        viewModelScope.launch {
     //       delay(2000L)
     //       job?.cancelChildren()
       //     viewModelScope.coroutineContext.cancelChildren()
        }
    }
}

