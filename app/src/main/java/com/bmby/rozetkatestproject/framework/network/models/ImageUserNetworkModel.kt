package com.bmby.rozetkatestproject.framework.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageUserNetworkModel (
    @SerializedName("id")
    @Expose
    var id: String?,

    @SerializedName("name")
    @Expose
    var name: String?,

    @SerializedName("location")
    @Expose
    var location: String?
        )