package com.idmdragon.data.di



import com.idmdragon.data.repository.PexelsRepositoryImpl
import com.idmdragon.domain.repository.PexelsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PexelsRepository> {
        PexelsRepositoryImpl(get(), get())
    }

}