package com.idmdragon.feature.di

import com.idmdragon.domain.usecase.PexelsUsecase
import com.idmdragon.domain.usecase.PexelsUsecaseImpl
import com.idmdragon.feature.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    factory<PexelsUsecase> {
        PexelsUsecaseImpl(get())
    }
    viewModel {
        HomeViewModel(get())
    }
}