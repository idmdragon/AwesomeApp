package com.idmdragon.domain.usecase

import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PexelsUsecase {
    fun getListPexels(): Flow<Resource<List<Pexels>>>
    fun getPexelsById(id: Int): Flow<Resource<Pexels>>

}