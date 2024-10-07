package me.androidbox.busbymoviesv2.move_list.data.remote_data_source.imp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.MovieListRemoteDataSource
import me.androidbox.busbymoviesv2.move_list.data.toMovieListModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieResultModel

class MovieListPagingRemoteDataSourceImp(
    private val movieListRemoteDataSource: MovieListRemoteDataSource,
    private val searchTerm: String,
    private val route: String,
) : PagingSource<Int, MovieResultModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResultModel>): Int? {
        println("GETREFERESHKEY")

        val pageKey = state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

        return pageKey
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResultModel> {
        println("LOAD")
        /** If the key is null we are at the first page (1) */
        val position = params.key ?: 1
        val offset = (position - 1) * 20 /* We want 20 pages */

        when(val response = movieListRemoteDataSource.movieList(route)) {
            is CheckResult.Failure -> {
                return LoadResult.Error(Throwable(message = response.exceptionError.toString()))
            }
            is CheckResult.Success -> {
                val nextKey = if(response.data.movieResultDto.isEmpty()) {
                    /** End of results */
                    null
                }
                else {
                    position + 1
                }

                val movieListModel = response.data.toMovieListModel()

                return LoadResult.Page(
                    data = movieListModel.movieResultModel,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = nextKey
                )
            }
        }
    }
}