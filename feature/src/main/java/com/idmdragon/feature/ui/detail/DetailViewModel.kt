package com.idmdragon.feature.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.usecase.PexelsUsecase
import com.idmdragon.domain.utils.Resource

class DetailViewModel(private val usecase: PexelsUsecase) : ViewModel() {

    fun getPexelsById(id: Int): LiveData<Resource<Pexels>> =
        usecase.getPexelsById(id).asLiveData()

}