package com.bmby.rozetkatestproject.framework.network

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.SearchNetworkImageModel
import okhttp3.ResponseBody

interface ImageRetrofitService {
    suspend fun get(): List<ImageNetworkEntity>

    suspend fun getById(id: String): ImageNetworkEntity

    suspend fun search(request: String): SearchNetworkImageModel

}