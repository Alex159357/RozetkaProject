package com.bmby.rozetkatestproject.framework.network.mappers

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.SearchNetworkImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor(): EntityMapper<ImageNetworkEntity, ImageModel> {
    override fun mapFromEntity(entity: ImageNetworkEntity): ImageModel {
        return ImageModel(
            id = entity.id,
            width = entity.width,
            height = entity.height,
            color = entity.color,
            likes = entity.likes,
            description = entity.description
        )
    }

    override fun mapToEntity(domainModel: ImageModel): ImageNetworkEntity {
        return ImageNetworkEntity(
            id = domainModel.id,
            width = domainModel.width,
            height = domainModel.height,
            color = domainModel.color,
            likes = domainModel.likes,
            description = domainModel.description
        )
    }

    fun mapFromEntityList(entities: List<ImageNetworkEntity>): List<ImageModel>{
        return entities.map { mapFromEntity(it) }
    }


}