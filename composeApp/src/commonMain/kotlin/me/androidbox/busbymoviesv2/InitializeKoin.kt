package me.androidbox.busbymoviesv2

import me.androidbox.busbymoviesv2.core.data.di.coreDataModule
import me.androidbox.busbymoviesv2.move_list.data.di.movieListDataModule
import me.androidbox.busbymoviesv2.move_list.di.movieListModule
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
            *platformSpecificModules
        )
    }
}