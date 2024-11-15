package me.androidbox.busbymoviesv2.move_list.data.repository.imp

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.MovieListRemoteDataSource
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.imp.MovieListPagingRemoteDataSourceImp
import me.androidbox.busbymoviesv2.move_list.data.repository.MovieListPagingRepository
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieResultModel

class MovieListPagingRepositoryImp(
    private val movieListRemoteDataSource: MovieListRemoteDataSource,
    private val searchTerm: String
): MovieListPagingRepository {
    override fun movieListPaging(route: String): Flow<PagingData<MovieResultModel>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
               MovieListPagingRemoteDataSourceImp(
                   movieListRemoteDataSource = movieListRemoteDataSource,
                   searchTerm = searchTerm,
                   route = route
               )
            }
        ).flow
    }
}