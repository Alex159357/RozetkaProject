package com.bmby.rozetkatestproject.framework.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageUrlsNetworkModel (
    @SerializedName("raw")
    @Expose
    var raw: Any?,

    @SerializedName("full")
    @Expose
    var full: Any?,

    @SerializedName("regular")
    @Expose
    var regular: Any?,

    @SerializedName("small")
    @Expose
    var small: Any?,

    @SerializedName("thumb")
    @Expose
    var thumb: Any?
        )