package com.idmdragon.data.source.remote.response

data class GeneralResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<PexelsResponse>
)