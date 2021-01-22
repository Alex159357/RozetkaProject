package com.bmby.rozetkatestproject.framework.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserProfilePicturesNetwork(

    @SerializedName("small")
    @Expose
    var small: String?,

    @SerializedName("medium")
    @Expose
    var medium: String?,

    @SerializedName("large")
    @Expose
    var large: String?

)