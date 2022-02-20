package com.idmdragon.domain.usecase

import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.repository.PexelsRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class PexelsUsecaseImpl(private val pexelsRepository: PexelsRepository): PexelsUsecase {
    override fun getListPexels(): Flow<Resource<List<Pexels>>> =
        pexelsRepository.getListPexels()

}