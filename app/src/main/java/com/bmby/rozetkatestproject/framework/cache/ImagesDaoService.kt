package com.bmby.rozetkatestproject.framework.cache

import com.bmby.rozetkatestproject.framework.cache.models.ImagesCacheEntity

interface ImagesDaoService {

    suspend fun insert(imageEntity: ImagesCacheEntity): Long

    suspend fun get(): List<ImagesCacheEntity>
}