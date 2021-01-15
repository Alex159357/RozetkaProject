package com.bmby.rozetkatestproject.framework.cache

import com.bmby.rozetkatestproject.framework.cache.database.ImagesDao
import com.bmby.rozetkatestproject.framework.cache.models.ImagesCacheEntity

class ImageDaoServiceImpl
constructor(private val imagesDao: ImagesDao) :ImagesDaoService
{
    override suspend fun insert(imageEntity: ImagesCacheEntity): Long {
       return imagesDao.insert(imageEntity)
    }

    override suspend fun get(): List<ImagesCacheEntity> {
        return imagesDao.get()
    }
}