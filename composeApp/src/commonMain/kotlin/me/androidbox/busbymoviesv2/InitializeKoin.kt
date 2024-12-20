package me.androidbox.busbymoviesv2

import me.androidbox.busbymoviesv2.configuration.di.configurationModule
import me.androidbox.busbymoviesv2.core.data.di.coreDataModule
import me.androidbox.busbymoviesv2.favourites.di.favouriteMovieModule
import me.androidbox.busbymoviesv2.move_list.data.di.movieListDataModule
import me.androidbox.busbymoviesv2.move_list.di.movieListModule
import me.androidbox.busbymoviesv2.movie_details.di.movieDetailModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initializeKoin(koinConfig: KoinAppDeclaration? = null, vararg platformSpecificModules: Module = emptyArray()) {

    startKoin {
        koinConfig?.invoke(this@startKoin)

        this.modules(
            coreDataModule,
            movieListDataModule,
            movieListModule,
            configurationModule,
            movieDetailModule,
            favouriteMovieModule,
            *platformSpecificModules
        )
    }
}