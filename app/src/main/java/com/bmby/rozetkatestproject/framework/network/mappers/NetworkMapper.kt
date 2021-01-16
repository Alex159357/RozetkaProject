package com.bmby.rozetkatestproject.framework.network.mappers

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.ImageUrlsNetworkModel
import com.bmby.rozetkatestproject.framework.network.models.ImageUserNetworkModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageUrlsModel
import com.bmby.rozetkatestproject.logic.domain.models.ImagesUserModel
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<ImageNetworkEntity, ImageModel> {
    override fun mapFromEntity(entity: ImageNetworkEntity): ImageModel {
        return ImageModel(
            id = entity.id,
            width = entity.width,
            height = entity.height,
            color = entity.color,
            likes = entity.likes,
            description = entity.description,
            urls = mapFromUrlsEntity(entity.urls),
            user = mapFromUserEntity(entity.user!!)
        )
    }

    override fun mapToEntity(domainModel: ImageModel): ImageNetworkEntity {
        return ImageNetworkEntity(
            id = domainModel.id,
            width = domainModel.width,
            height = domainModel.height,
            color = domainModel.color,
            likes = domainModel.likes,
            description = domainModel.description,
            urls = mapToUrlsEntity(domainModel.urls!!),
            user = mapToUserEntity(domainModel.user!!)
        )
    }

    private fun mapToUserEntity(subEntity: ImagesUserModel): ImageUserNetworkModel {
        return ImageUserNetworkModel(
            id = subEntity.id,
            name = subEntity.name,
            location = subEntity.location
        )
    }

    private fun mapFromUserEntity(subEntity: ImageUserNetworkModel): ImagesUserModel {
        return ImagesUserModel(
            id = subEntity.id!!,
            name = subEntity.name!!,
            location = if(subEntity.location == null) "No location" else subEntity.location!!
        )
    }

    private fun mapFromUrlsEntity(subEntity: ImageUrlsNetworkModel): ImageUrlsModel {
        return ImageUrlsModel(
            raw = subEntity.raw.toString(),
            full = subEntity.full.toString(),
            regular = subEntity.regular.toString(),
            small = subEntity.small.toString(),
            thumb = subEntity.thumb.toString(),
        )
    }

    private fun mapToUrlsEntity(subDomainModel: ImageUrlsModel): ImageUrlsNetworkModel {
        return ImageUrlsNetworkModel(
            raw = subDomainModel.raw,
            full = subDomainModel.full,
            regular = subDomainModel.regular,
            small = subDomainModel.small,
            thumb = subDomainModel.thumb,
        )
    }


    fun mapFromEntityList(entities: List<ImageNetworkEntity>): List<ImageModel> {
        return entities.map {
            mapFromEntity(it)
        }
    }


}