package me.androidbox.busbymoviesv2.movie_details.di

import io.ktor.client.HttpClient
import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.movie_details.data.entities.MovieDetailDatabase
import me.androidbox.busbymoviesv2.movie_details.data.local_data_source.imp.MovieDetailLocalDataSource
import me.androidbox.busbymoviesv2.movie_details.data.local_data_source.imp.MovieDetailLocalDataSourceImp
import me.androidbox.busbymoviesv2.movie_details.data.remote_data_source.MovieDetailRemoteDataSource
import me.androidbox.busbymoviesv2.movie_details.data.remote_data_source.imp.MovieDetailRemoteDataSourceImp
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.data.repository.imp.MovieDetailRepositoryImp
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.DeleteFavouriteMovieUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.GetFavouriteMoviesUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.IsFavouriteMovieUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.MovieCreditsUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.MovieDetailUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.SaveFavouriteMovieUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.SimilarMoviesUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp.DeleteFavouriteMovieUseCaseImp
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp.GetFavouriteMoviesUseCaseImp
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp.IsFavouriteMovieUseCaseImp
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp.MovieCreditsUseCaseImp
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp.MovieDetailUseCaseImp
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp.SaveFavouriteMovieUseCaseImp
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp.SimilarMoviesUseCaseImp
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val movieDetailModule = module {
    factory<MovieDetailUseCase> {
        MovieDetailUseCaseImp(get<MovieDetailRepository>())
    }

    factoryOf(::SimilarMoviesUseCaseImp).bind<SimilarMoviesUseCase>()
    factoryOf(::GetFavouriteMoviesUseCaseImp).bind(GetFavouriteMoviesUseCase::class)
    factoryOf(::IsFavouriteMovieUseCaseImp).bind(IsFavouriteMovieUseCase::class)
    factoryOf(::DeleteFavouriteMovieUseCaseImp).bind(DeleteFavouriteMovieUseCase::class)
    factoryOf(::SaveFavouriteMovieUseCaseImp).bind(SaveFavouriteMovieUseCase::class)

    factory<MovieDetailRemoteDataSource> {
        MovieDetailRemoteDataSourceImp(get<HttpClient>())
    }

    factory<MovieDetailLocalDataSource> {
        MovieDetailLocalDataSourceImp(get<MovieDetailDatabase>())
    }

    factory<MovieDetailRepository> {
        MovieDetailRepositoryImp(
            get<MovieDetailRemoteDataSource>(),
            get<MovieDetailLocalDataSource>())
    }

    viewModel { (movieId: Int) ->
        MovieDetailViewModel(
            movieDetailUseCase = get<MovieDetailUseCase>(),
            configurationUseCase = get<ConfigurationUseCase>(),
            movieCreditsUseCase = get<MovieCreditsUseCase>(),
            similarMoviesUseCase = get<SimilarMoviesUseCase>(),
            isFavouriteMovieUseCase = get<IsFavouriteMovieUseCase>(),
            deleteFavouriteMovieUseCase = get<DeleteFavouriteMovieUseCase>(),
            saveFavouriteMovieUseCase = get<SaveFavouriteMovieUseCase>(),
            movieId = movieId
        )
    }

    factory<MovieCreditsUseCase> {
        MovieCreditsUseCaseImp(get<MovieDetailRepository>())
    }
}