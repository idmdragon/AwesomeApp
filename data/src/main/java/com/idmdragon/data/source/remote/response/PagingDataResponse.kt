package com.idmdragon.data.source.remote.response

data class PagingDataResponse<DataType>(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<DataType>
)