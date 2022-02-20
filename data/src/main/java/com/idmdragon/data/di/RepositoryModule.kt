package com.idmdragon.data.di



import androidx.paging.ExperimentalPagingApi
import com.idmdragon.data.repository.PexelsRepositoryImpl
import com.idmdragon.domain.repository.PexelsRepository
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val repositoryModule = module {
    single<PexelsRepository> {
        PexelsRepositoryImpl(get(), get())
    }
}