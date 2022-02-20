package com.idmdragon.data.repository

import com.idmdragon.data.mapper.toEntities
import com.idmdragon.data.mapper.toEntity
import com.idmdragon.data.mapper.toFlowModel
import com.idmdragon.data.mapper.toFlowModels
import com.idmdragon.data.source.NetworkBoundResource
import com.idmdragon.data.source.local.LocalDataSource
import com.idmdragon.data.source.remote.RemoteDataSource
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.PexelsResponse
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.repository.PexelsRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class PexelsRepositoryImpl(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : PexelsRepository {

    override fun getListPexels(): Flow<Resource<List<Pexels>>> =
        object : NetworkBoundResource<List<Pexels>, List<PexelsResponse>>() {
            override fun loadFromDB(): Flow<List<Pexels>> =
                local.getAllPexels().toFlowModels()

            override fun shouldFetch(data: List<Pexels>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<PexelsResponse>>> =
                remote.getAllPexels()

            override suspend fun saveCallResult(data: List<PexelsResponse>) =
                local.insertListPexels(data.toEntities())

        }.asFlow()

    override fun getPexelsById(id: Int): Flow<Resource<Pexels>> =
        object : NetworkBoundResource<Pexels, PexelsResponse>() {
            override fun loadFromDB(): Flow<Pexels> =
                local.getPexelsById(id).toFlowModel()

            override fun shouldFetch(data: Pexels?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<PexelsResponse>> =
                remote.getPexelsById(id)

            override suspend fun saveCallResult(data: PexelsResponse) =
                local.insertPexels(data.toEntity())

        }.asFlow()
}