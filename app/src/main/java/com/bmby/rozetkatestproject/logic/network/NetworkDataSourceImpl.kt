package com.bmby.rozetkatestproject.logic.network

import com.bmby.rozetkatestproject.framework.network.ImageRetrofitService
import com.bmby.rozetkatestproject.framework.network.downloader.ImageDownloader
import com.bmby.rozetkatestproject.framework.network.mappers.NetworkMapper
import com.bmby.rozetkatestproject.framework.network.models.SearchNetworkImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import okhttp3.ResponseBody

class NetworkDataSourceImpl constructor(
    private val imageRetrofitService: ImageRetrofitService,
    private val networkMapper: NetworkMapper,
    private val imageDownloader: ImageDownloader
):NetworkDataSource {
    override suspend fun get(): List<ImageModel> {
        return networkMapper.mapFromEntityList(imageRetrofitService.get())
    }

    override suspend fun getById(id: String): ImageModel {
       return networkMapper.mapFromEntity(imageRetrofitService.getById(id))
    }

    override suspend fun search(request: String): List<ImageModel> {
        return networkMapper.mapFromEntityList(imageRetrofitService.search(request).results)
    }

    override suspend fun downloadImage(url: String): Int {
        return imageDownloader.downloadImage(url)
    }
}