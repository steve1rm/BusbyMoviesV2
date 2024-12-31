package me.androidbox.busbymoviesv2.actors.presentation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.core.domain.utils.onFailure
import me.androidbox.busbymoviesv2.core.domain.utils.onSuccess
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.MovieCreditsUseCase
import me.androidbox.busbymoviesv2.movie_details.presentation.toCredits

class ActorsViewModel(
    private val movieCreditsUseCase: MovieCreditsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val lazyListState = LazyListState()

    val shouldShowFab = snapshotFlow {
        lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = false
    )

    private var hasFetched = false

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }

    private val _actorState = MutableStateFlow<ActorState>(ActorState())
    val actorState = _actorState.asStateFlow()
        .onStart {
            if(!hasFetched) {
                fetchActors()
                hasFetched = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ActorState()
        )


    fun saveMovieId(movieId: Int) {
        savedStateHandle[MOVIE_ID] = movieId
    }

    fun fetchActors() {
        viewModelScope.launch {
            try { /** do something here if the movie id is null, Don't display a blank screen */
                val movieId = savedStateHandle.get<Int>(MOVIE_ID)

                if(movieId  != null) {
                    _actorState.update {  actorState ->
                        actorState.copy(isLoading = true)
                    }

                    movieCreditsUseCase.execute(movieId)
                        .onSuccess { creditsModel ->
                            _actorState.update {  actorState ->
                                actorState.copy(
                                    isLoading = false,
                                    actors = creditsModel.toCredits().cast)
                            }
                        }
                        .onFailure { error, errorModel ->
                            _actorState.update {  actorState ->
                                actorState.copy(isLoading = false)
                            }
                        }
                }
            }
            catch (exception: Exception) {
                _actorState.update {  actorState ->
                    actorState.copy(isLoading = false)
                }
                exception.printStackTrace()
            }
        }
    }
}