package com.idmdragon.domain.usecase

import androidx.paging.PagingData
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.repository.PexelsRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class PexelsUsecaseImpl(private val pexelsRepository: PexelsRepository): PexelsUsecase {
    override fun getListPexels(): Flow<PagingData<Pexels>> =
        pexelsRepository.getListPexels()

    override fun getPexelsById(id: Int): Flow<Resource<Pexels>> =
        pexelsRepository.getPexelsById(id)
}