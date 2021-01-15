package com.bmby.rozetkatestproject.framework.network

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.SearchNetworkImageModel

interface ImageRetrofitService {
    suspend fun get(): List<ImageNetworkEntity>

    suspend fun search(request: String): SearchNetworkImageModel
}