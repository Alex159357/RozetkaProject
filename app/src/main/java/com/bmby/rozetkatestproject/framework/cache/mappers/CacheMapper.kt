package com.bmby.rozetkatestproject.framework.cache.mappers

import com.bmby.rozetkatestproject.framework.cache.models.ImagesCacheEntity
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<ImagesCacheEntity, ImageModel> {
    override fun mapFromEntity(entity: ImagesCacheEntity): ImageModel {
        return ImageModel(
            id = entity.id,
            width = entity.width,
            height = entity.height,
            color = entity.color,
            likes = entity.likes,
            description = entity.description
        )
    }

    override fun mapToEntity(domainModel: ImageModel): ImagesCacheEntity {
        return ImagesCacheEntity(
            id = domainModel.id!!,
            width = domainModel.width!!,
            height = domainModel.height!!,
            color = domainModel.color!!,
            likes = domainModel.likes!!,
            description = domainModel.description!!
        )
    }

    fun mapFromEntityList(entities: List<ImagesCacheEntity>): List<ImageModel>{
        return entities.map { mapFromEntity(it) }
    }

}