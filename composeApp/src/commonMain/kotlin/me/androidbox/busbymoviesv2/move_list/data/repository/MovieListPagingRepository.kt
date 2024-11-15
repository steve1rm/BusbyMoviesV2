package me.androidbox.busbymoviesv2.move_list.data.repository

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieResultModel

interface MovieListPagingRepository {
    fun movieListPaging(route: String): Flow<PagingData<MovieResultModel>>
}