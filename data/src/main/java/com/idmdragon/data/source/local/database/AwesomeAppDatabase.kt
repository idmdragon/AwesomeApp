package com.idmdragon.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idmdragon.data.source.local.database.dao.AwesomeDao
import com.idmdragon.data.source.local.entity.PexelsEntity


@Database(
    entities = [ PexelsEntity::class ],
    version = 1,
    exportSchema = false
)
abstract class AwesomeAppDatabase : RoomDatabase() {

    abstract fun awesomeDao(): AwesomeDao


}