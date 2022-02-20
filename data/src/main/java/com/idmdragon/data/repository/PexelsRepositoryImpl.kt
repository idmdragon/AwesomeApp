package com.idmdragon.data.repository

import androidx.paging.*
import com.idmdragon.data.mapper.toEntities
import com.idmdragon.data.mapper.toEntity
import com.idmdragon.data.mapper.toFlowModel
import com.idmdragon.data.mapper.toModel
import com.idmdragon.data.source.NetworkBoundResource
import com.idmdragon.data.source.PagingMediator
import com.idmdragon.data.source.local.LocalDataSource
import com.idmdragon.data.source.local.entity.PexelsEntity
import com.idmdragon.data.source.remote.RemoteDataSource
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.PagingDataResponse
import com.idmdragon.data.source.remote.response.PexelsResponse
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.repository.PexelsRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class PexelsRepositoryImpl(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : PexelsRepository {

    override fun getListPexels(): Flow<PagingData<Pexels>> =
        Pager(
            config = PagingConfig(15, enablePlaceholders = true, initialLoadSize = 15),
            pagingSourceFactory = { local.getAllPexels() },
            remoteMediator = object : PagingMediator<PexelsEntity, PexelsResponse>() {
                override suspend fun createCall(page: Int): PagingDataResponse<PexelsResponse> =
                    remote.getAllPexels(page)

                override suspend fun saveCallResult(response: List<PexelsResponse>) {
                    local.insertListPexels(response.toEntities())
                }
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toModel()
            }
        }

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