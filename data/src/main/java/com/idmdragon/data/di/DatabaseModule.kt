package com.idmdragon.data.di

import androidx.room.Room
import com.idmdragon.data.source.local.LocalDataSource
import com.idmdragon.data.source.local.database.AwesomeAppDatabase

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AwesomeAppDatabase::class.java,
            "AwesomeApps.db"
        ).fallbackToDestructiveMigration().build()
    }
    factory {
        get<AwesomeAppDatabase>().awesomeDao()
    }
}

val localSourceModule = module {
    single {
        LocalDataSource(get())
    }

}