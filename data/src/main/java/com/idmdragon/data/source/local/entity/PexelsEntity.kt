package com.idmdragon.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PexelsEntity(
    @PrimaryKey
    val id: Int,
    val description: String,
    val color: String,
    val photographer: String,
    val url: String,
    val originalImage: String,
    val smallImage: String
)