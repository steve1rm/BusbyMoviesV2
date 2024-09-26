package me.androidbox.busbymoviesv2

import android.app.Application
import me.androidbox.busbymoviesv2.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class BusbyMoviesV2Application : Application() {
    override fun onCreate() {
        super.onCreate()

        initializeKoin(
            koinConfig = {
                androidLogger()
                androidContext(this@BusbyMoviesV2Application)
            },
            androidModule
        )
    }
}