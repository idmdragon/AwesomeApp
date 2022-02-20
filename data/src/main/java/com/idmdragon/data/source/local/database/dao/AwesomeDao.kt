package com.idmdragon.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idmdragon.data.source.local.entity.PexelsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AwesomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListPexels(pexelsEntities: List<PexelsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPexels(pexels: PexelsEntity)

    @Query("SELECT * FROM PexelsEntity")
    fun selectAllPexels(): Flow<List<PexelsEntity>>

    @Query("SELECT * FROM PexelsEntity WHERE id = :id")
    fun selectPexelsById(id:Int): Flow<PexelsEntity>
}