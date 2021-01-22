package com.bmby.rozetkatestproject.framework.network.mappers

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.UserProfilePicturesNetwork
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.models.UserProfileImage
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import javax.inject.Inject

class ProfilePicturesMapper
@Inject constructor(): EntityMapper<UserProfilePicturesNetwork, UserProfileImage> {

    override fun mapFromEntity(entity: UserProfilePicturesNetwork): UserProfileImage {
        return UserProfileImage(
            small = entity.small!!,
            medium = entity.medium!!,
            large = entity.large!!
        )
    }

    override fun mapToEntity(domainModel: UserProfileImage): UserProfilePicturesNetwork {
        return UserProfilePicturesNetwork(
            small = domainModel.small,
            medium = domainModel.medium,
            large = domainModel.large
        )
    }
}