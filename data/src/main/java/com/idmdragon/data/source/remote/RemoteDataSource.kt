package com.idmdragon.data.source.remote

import android.util.Log
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.PagingDataResponse
import com.idmdragon.data.source.remote.response.PexelsResponse
import com.idmdragon.data.source.remote.service.PexelsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val pexelsService: PexelsService) {

    suspend fun getAllPexels(page: Int): PagingDataResponse<PexelsResponse> =
        pexelsService.getListItem(page)

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