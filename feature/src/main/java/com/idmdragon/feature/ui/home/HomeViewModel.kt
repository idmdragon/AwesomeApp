package com.idmdragon.feature.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.usecase.PexelsUsecase
import com.idmdragon.domain.utils.Resource

class HomeViewModel(private val useCase: PexelsUsecase): ViewModel() {
    fun getAllPexels():LiveData<PagingData<Pexels>> =
        useCase.getListPexels().asLiveData()

}