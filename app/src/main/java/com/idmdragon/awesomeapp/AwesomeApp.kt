package com.idmdragon.awesomeapp

import android.app.Application
import com.idmdragon.data.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin


class AwesomeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AwesomeApp)

            loadKoinModules(
                listOf(

                )
            )
        }
    }
}