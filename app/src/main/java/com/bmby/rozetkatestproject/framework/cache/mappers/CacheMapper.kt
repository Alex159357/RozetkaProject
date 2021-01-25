package com.bmby.rozetkatestproject.framework.cache.mappers

import com.bmby.rozetkatestproject.framework.cache.models.ImagesCacheEntity
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageUrlsModel
import com.bmby.rozetkatestproject.logic.domain.models.ImagesUserModel
import com.bmby.rozetkatestproject.logic.domain.models.UserProfileImage
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<ImagesCacheEntity, ImageModel> {
    override fun mapFromEntity(entity: ImagesCacheEntity): ImageModel {
        return ImageModel(
            entity.id, null, null, null, null, null,
            urls = ImageUrlsModel(
                raw = null,
                full = entity.full,
                regular = null,
                small = null,
                thumb = entity.thumb
            ),
            user = ImagesUserModel(
                name = entity.userName,
                id = entity.userId,
                location = "",
                profile_image = null
            ),
            savedDate = entity.savedDate,
        )
    }

    override fun mapToEntity(domainModel: ImageModel): ImagesCacheEntity {
        return ImagesCacheEntity(
            id = domainModel.id!!,
            thumb = domainModel.urls!!.thumb!!,
            full = domainModel.urls!!.full!!,
            savedDate = domainModel.savedDate!!,
            userName = domainModel.user!!.name,
            userId = domainModel.user!!.id
        )
    }

    fun mapFromEntityList(entities: List<ImagesCacheEntity>): List<ImageModel> {
        return entities.map { mapFromEntity(it) }
    }


}