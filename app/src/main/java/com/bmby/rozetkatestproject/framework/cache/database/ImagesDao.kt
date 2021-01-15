package com.bmby.rozetkatestproject.framework.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bmby.rozetkatestproject.framework.cache.models.ImagesCacheEntity

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blog: ImagesCacheEntity): Long

    @Query("SELECT * FROM images")
    suspend fun get(): List<ImagesCacheEntity>

}