package com.bmby.rozetkatestproject.framework.network

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.SearchNetworkImageModel
import com.bmby.rozetkatestproject.framework.network.retrofit.ImageRetrofit
import okhttp3.ResponseBody

class ImageRetrofitServiceImpl constructor(
    private val imageRetrofit: ImageRetrofit
) :ImageRetrofitService {
    override suspend fun get(): List<ImageNetworkEntity> {
        return imageRetrofit.get("ytX0jueCagugbalpOGNdM-Rf26c1rStBFW4WcXhV1rE", 100, 1)
    }

    override suspend fun getById(id: String): ImageNetworkEntity {
        return imageRetrofit.getById(id, "ytX0jueCagugbalpOGNdM-Rf26c1rStBFW4WcXhV1rE", 100)
    }

    override suspend fun search(request: String): SearchNetworkImageModel {
        return imageRetrofit.search("ytX0jueCagugbalpOGNdM-Rf26c1rStBFW4WcXhV1rE", request, 100)
    }
}