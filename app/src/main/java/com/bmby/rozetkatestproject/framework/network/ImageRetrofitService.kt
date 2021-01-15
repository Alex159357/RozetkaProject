package com.bmby.rozetkatestproject.framework.network

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity

interface ImageRetrofitService {
    suspend fun get(): List<ImageNetworkEntity>

    suspend fun search(request: String): List<ImageNetworkEntity>
}