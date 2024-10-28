package me.androidbox.busbymoviesv2.movie_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.movie_details.domain.MovieDetailUseCase

class MovieDetailViewModel(private val movieDetailUseCase: MovieDetailUseCase) : ViewModel() {

    fun movieDetail(movieId: Int) {
        viewModelScope.launch {
            val movieDetail = movieDetailUseCase.execute(movieId)

            println(movieDetail.toString())
        }
    }
}