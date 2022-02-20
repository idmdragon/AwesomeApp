package com.idmdragon.domain.usecase

import androidx.paging.PagingData
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PexelsUsecase {
    fun getListPexels(): Flow<PagingData<Pexels>>
    fun getPexelsById(id: Int): Flow<Resource<Pexels>>

}