package com.bmby.rozetkatestproject.framework.cache.mappers

import com.bmby.rozetkatestproject.framework.cache.models.ImagesCacheEntity
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<ImagesCacheEntity, ImageModel> {
    override fun mapFromEntity(entity: ImagesCacheEntity): ImageModel {
        return ImageModel(entity.id, null, null, null, null, null, null, null)
    }

    override fun mapToEntity(domainModel: ImageModel): ImagesCacheEntity {
        return ImagesCacheEntity(
            id = domainModel.id!!
        )
    }

    fun mapFromEntityList(entities: List<ImagesCacheEntity>): List<ImageModel>{
        return entities.map { mapFromEntity(it) }
    }


}