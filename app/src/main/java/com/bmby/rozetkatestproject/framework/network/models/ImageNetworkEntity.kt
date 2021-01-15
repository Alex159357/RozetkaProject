package com.bmby.rozetkatestproject.framework.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageNetworkEntity(
    @SerializedName("id")
    @Expose
    var id: String?,

    @SerializedName("width")
    @Expose
    var width: Int?,

    @SerializedName("height")
    @Expose
    var height: Int?,

    @SerializedName("color")
    @Expose
    var color: String?,

    @SerializedName("likes")
    @Expose
    var likes: Int?,

    @SerializedName("alt_description")
    @Expose
    var description: String?,
)