package com.idmdragon.data.repository.remote.mapper

import com.idmdragon.data.repository.remote.response.PexelsResponse
import com.idmdragon.domain.model.Pexels

fun PexelsResponse.toModel(): Pexels =
    Pexels(
        description = alt,
        color = avg_color,
        id = id,
        url = url,
        originalImage = src.original,
        mediumImage = src.medium,
        photographer = photographer
    )