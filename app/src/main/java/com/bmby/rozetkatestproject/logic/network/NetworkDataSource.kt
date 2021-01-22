package com.bmby.rozetkatestproject.logic.network

import com.bmby.rozetkatestproject.framework.network.models.SearchNetworkImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import okhttp3.ResponseBody

interface NetworkDataSource {
    suspend fun get(): List<ImageModel>

    suspend fun getById(id:String):ImageModel

    suspend fun search(request: String): List<ImageModel>

    suspend fun downloadImage(url: String): Int

}