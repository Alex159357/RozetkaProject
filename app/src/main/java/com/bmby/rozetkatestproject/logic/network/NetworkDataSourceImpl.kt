package com.bmby.rozetkatestproject.logic.network

import com.bmby.rozetkatestproject.framework.network.ImageRetrofitService
import com.bmby.rozetkatestproject.framework.network.mappers.NetworkMapper
import com.bmby.rozetkatestproject.framework.network.models.SearchNetworkImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel

class NetworkDataSourceImpl constructor(
    private val imageRetrofitService: ImageRetrofitService,
    private val networkMapper: NetworkMapper
):NetworkDataSource {
    override suspend fun get(): List<ImageModel> {
        return networkMapper.mapFromEntityList(imageRetrofitService.get())
    }

    override suspend fun search(request: String): List<ImageModel> {
        return networkMapper.mapFromEntityList(imageRetrofitService.search(request).results)
    }
}