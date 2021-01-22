package com.bmby.rozetkatestproject.framework.network.mappers

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.ImageUserNetworkModel
import com.bmby.rozetkatestproject.framework.network.models.UserProfilePicturesNetwork
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImagesUserModel
import com.bmby.rozetkatestproject.logic.domain.models.UserProfileImage
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import javax.inject.Inject

class UserMapper
@Inject
constructor(private val profilePicturesMapper: ProfilePicturesMapper):
    EntityMapper<ImageUserNetworkModel, ImagesUserModel> {
    override fun mapFromEntity(entity: ImageUserNetworkModel): ImagesUserModel {
        return ImagesUserModel(
            id = entity.id!!,
            name = entity.name!!,
            location = if (entity.location == null) "No location" else entity.location!!,
            profile_image = profilePicturesMapper.mapFromEntity(entity.profileImage!!)
        )
    }

    override fun mapToEntity(domainModel: ImagesUserModel): ImageUserNetworkModel {
        return ImageUserNetworkModel(
            id = domainModel.id,
            name = domainModel.name,
            location = domainModel.location,
            profileImage = profilePicturesMapper.mapToEntity(domainModel.profile_image!!)
        )
    }

}