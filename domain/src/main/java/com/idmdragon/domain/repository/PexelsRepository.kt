package com.idmdragon.domain.repository

import androidx.paging.PagingData
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PexelsRepository {
    fun getListPexels(): Flow<PagingData<Pexels>>
    fun getPexelsById(id: Int): Flow<Resource<Pexels>>
}