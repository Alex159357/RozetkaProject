package com.bmby.rozetkatestproject.framework.network.models

import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchNetworkImageModel(
    @SerializedName("results")
    @Expose
    var results: List<ImageNetworkEntity>
)