package com.bmby.rozetkatestproject.logic.domain.models

data class ImagesUserModel(
    var id: String,
    var name: String,
    var location: String,
    var profile_image: UserProfileImage?
)