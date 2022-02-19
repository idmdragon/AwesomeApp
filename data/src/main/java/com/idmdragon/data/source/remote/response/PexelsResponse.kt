package com.idmdragon.data.source.remote.response

data class PexelsResponse(
    val alt: String,
    val avg_color: String,
    val id: Int,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val src: Src,
    val url: String,
)