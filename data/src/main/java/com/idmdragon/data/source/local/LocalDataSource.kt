package com.idmdragon.data.source.local

import com.idmdragon.data.source.local.database.dao.AwesomeDao
import com.idmdragon.data.source.local.entity.PexelsEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val awesomeDao: AwesomeDao) {
    fun getAllPexels(): Flow<List<PexelsEntity>> =
        awesomeDao.selectAllPexels()

    suspend fun insertPexels(listPexels: List<PexelsEntity>) =
        awesomeDao.insertPexels(listPexels)

}