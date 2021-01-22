package com.bmby.rozetkatestproject.logic.domain.models

data class ImageModel (
    var id: String?,
    var width: Int?,
    var height: Int?,
    var color: String?,
    var likes: Int?,
    var description: String?,
//    var tags: List<ImageTagsModel>,
    var urls: ImageUrlsModel?,
    var user: ImagesUserModel?
)