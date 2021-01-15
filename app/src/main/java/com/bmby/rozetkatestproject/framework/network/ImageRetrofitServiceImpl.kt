package com.bmby.rozetkatestproject.framework.network

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.SearchNetworkImageModel
import com.bmby.rozetkatestproject.framework.network.retrofit.ImageRetrofit

class ImageRetrofitServiceImpl constructor(
    private val imageRetrofit: ImageRetrofit
) :ImageRetrofitService {
    override suspend fun get(): List<ImageNetworkEntity> {
        return imageRetrofit.get("ytX0jueCagugbalpOGNdM-Rf26c1rStBFW4WcXhV1rE", 30, 1)
    }

    override suspend fun search(request: String): SearchNetworkImageModel {
        return imageRetrofit.search("ytX0jueCagugbalpOGNdM-Rf26c1rStBFW4WcXhV1rE", request, 100)
    }

    //TODO Finish search
    //TODO create ui
    //TODO Create ui and logic connection
}