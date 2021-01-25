package com.bmby.rozetkatestproject.framework.cache

import com.bmby.rozetkatestproject.framework.cache.models.ImagesCacheEntity
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel

interface ImagesDaoService {

    suspend fun insert(imageEntity: ImagesCacheEntity): Long

    suspend fun get(): List<ImagesCacheEntity>

    suspend fun getSortedByDate(): List<ImagesCacheEntity>

    suspend fun getSortedByUser(): List<ImagesCacheEntity>
}