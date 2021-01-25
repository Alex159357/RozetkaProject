package com.bmby.rozetkatestproject.logic.cache

import com.bmby.rozetkatestproject.logic.domain.models.ImageModel

interface CacheDataSource {

    suspend fun insert(image: ImageModel): Long

    suspend fun insertList(images: List<ImageModel>)

    suspend fun get(): List<ImageModel>

    suspend fun getSortedByDate(): List<ImageModel>

    suspend fun getSortedByUser(): List<ImageModel>




}