package com.idmdragon.data.mapper

import com.idmdragon.data.source.local.entity.PexelsEntity
import com.idmdragon.data.source.remote.response.PexelsResponse
import com.idmdragon.domain.model.Pexels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun PexelsResponse.toEntity(): PexelsEntity =
    PexelsEntity(
        description = alt,
        color = avg_color,
        id = id,
        url = url,
        originalImage = src.original,
        smallImage = src.medium,
        photographer = photographer
    )

fun PexelsEntity.toModel(): Pexels =
    Pexels(
        description = description,
        color = color,
        id = id,
        url = url,
        originalImage = originalImage,
        smallImage = smallImage,
        photographer = photographer
    )


fun List<PexelsResponse>.toEntities(): List<PexelsEntity> =
    this.map {
        it.toEntity()
    }


fun List<PexelsEntity>.toModels(): List<Pexels> =
    this.map {
        it.toModel()
    }

fun Flow<List<PexelsEntity>>.toFlowModels(): Flow<List<Pexels>> =
    this.map {
        it.toModels()
    }