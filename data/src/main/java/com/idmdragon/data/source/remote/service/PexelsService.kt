package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.PagingDataResponse
import com.idmdragon.data.source.remote.response.PexelsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsService {

    @GET("/v1/curated")
    suspend fun getListItem( @Query("page") page: Int): PagingDataResponse<PexelsResponse>

    @GET("/v1/photos/{id}")
    suspend fun getPexelsById(@Path("id") id: String): PexelsResponse

}