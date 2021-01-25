package com.bmby.rozetkatestproject.framework.network.mappers

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.ImageUrlsNetworkModel
import com.bmby.rozetkatestproject.framework.network.models.ImageUserNetworkModel
import com.bmby.rozetkatestproject.framework.network.models.UserProfilePicturesNetwork
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageUrlsModel
import com.bmby.rozetkatestproject.logic.domain.models.ImagesUserModel
import com.bmby.rozetkatestproject.logic.domain.models.UserProfileImage
import com.bmby.rozetkatestproject.logic.domain.states.ImageDownloadState
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import okhttp3.ResponseBody
import javax.inject.Inject

class NetworkMapper
@Inject
constructor(
    private val urlsMapper: UrlsMapper,
    private val userMapper: UserMapper
) : EntityMapper<ImageNetworkEntity, ImageModel> {
    override fun mapFromEntity(entity: ImageNetworkEntity): ImageModel {
        return ImageModel(
            id = entity.id,
            width = entity.width,
            height = entity.height,
            color = entity.color,
            likes = entity.likes,
            description = entity.description,
            urls = urlsMapper.mapFromEntity(entity.urls),
            user = userMapper.mapFromEntity(entity.user!!),
            savedDate = null
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
            urls = urlsMapper.mapToEntity(domainModel.urls!!),
            user = userMapper.mapToEntity(domainModel.user!!)
        )
    }

    fun mapFromEntityList(entities: List<ImageNetworkEntity>): List<ImageModel> {
        return entities.map {
            mapFromEntity(it)
        }
    }


}