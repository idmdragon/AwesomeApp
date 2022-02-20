package com.idmdragon.feature.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.usecase.PexelsUsecase
import com.idmdragon.domain.utils.Resource

class HomeViewModel(private val useCase: PexelsUsecase): ViewModel() {
    fun getAllPexels():LiveData<Resource<List<Pexels>>> = useCase.getListPexels().asLiveData()

}