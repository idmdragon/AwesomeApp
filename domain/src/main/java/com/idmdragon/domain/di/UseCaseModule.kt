package com.idmdragon.domain.di


import com.idmdragon.domain.usecase.PexelsUsecase
import com.idmdragon.domain.usecase.PexelsUsecaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<PexelsUsecase> {
        PexelsUsecaseImpl(get())
    }

}