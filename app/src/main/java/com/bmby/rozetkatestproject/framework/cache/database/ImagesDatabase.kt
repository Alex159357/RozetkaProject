package com.bmby.rozetkatestproject.framework.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bmby.rozetkatestproject.framework.cache.models.ImagesCacheEntity


@Database(entities = [ImagesCacheEntity::class ], version = 1)
abstract class ImagesDatabase: RoomDatabase()  {

    abstract fun blogDao(): ImagesDao

    companion object{
        const val DATABASE_NAME: String = "images_db"
    }
}