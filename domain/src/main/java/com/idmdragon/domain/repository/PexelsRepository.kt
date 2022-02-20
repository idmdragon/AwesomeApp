package com.idmdragon.domain.repository

import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PexelsRepository {
    fun getListPexels(): Flow<Resource<List<Pexels>>>

}