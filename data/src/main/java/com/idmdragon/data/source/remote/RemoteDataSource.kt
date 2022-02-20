package com.idmdragon.data.source.remote

import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.PexelsResponse
import com.idmdragon.data.source.remote.service.PexelsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val pexelsService: PexelsService) {

    fun getAllPexels(): Flow<ApiResponse<List<PexelsResponse>>> =
        flow {
            try {
                val response = pexelsService.getListItem()
                emit(ApiResponse.Success(response.photos))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getPexelsById(id: Int): Flow<ApiResponse<PexelsResponse>> =
        flow {
            try {
                val response = pexelsService.getPexelsById(id.toString())
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}