package com.bmby.rozetkatestproject.logic.cache

import com.bmby.rozetkatestproject.framework.cache.ImagesDaoService
import com.bmby.rozetkatestproject.framework.cache.mappers.CacheMapper
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel

class CacheDataSourceImpl constructor(
    private val imageDaoService: ImagesDaoService,
    private val cacheMapper: CacheMapper
) : CacheDataSource {
    override suspend fun insert(image: ImageModel): Long {
        return imageDaoService.insert(cacheMapper.mapToEntity(image))
    }

    override suspend fun insertList(images: List<ImageModel>) {
        for (image in images) {
            imageDaoService.insert(cacheMapper.mapToEntity(image))
        }
    }

    override suspend fun get(): List<ImageModel> {
        return cacheMapper.mapFromEntityList(imageDaoService.get())
    }

    override suspend fun getSortedByDate(): List<ImageModel> {
        return cacheMapper.mapFromEntityList(imageDaoService.getSortedByDate())
    }

    override suspend fun getSortedByUser(): List<ImageModel> {
        return cacheMapper.mapFromEntityList(imageDaoService.getSortedByUser())
    }


}