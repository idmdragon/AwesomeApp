package com.idmdragon.data.source.local

import com.idmdragon.data.source.local.database.dao.AwesomeDao
import com.idmdragon.data.source.local.entity.PexelsEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val awesomeDao: AwesomeDao) {
    fun getAllPexels(): Flow<List<PexelsEntity>> =
        awesomeDao.selectAllPexels()

    suspend fun insertListPexels(listPexels: List<PexelsEntity>) =
        awesomeDao.insertListPexels(listPexels)

    suspend fun insertPexels(pexels: PexelsEntity) =
        awesomeDao.insertPexels(pexels)

    fun getPexelsById(id: Int): Flow<PexelsEntity> =
        awesomeDao.selectPexelsById(id)
}