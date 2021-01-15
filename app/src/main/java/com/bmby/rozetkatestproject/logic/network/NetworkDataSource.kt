package com.bmby.rozetkatestproject.logic.network

import com.bmby.rozetkatestproject.logic.domain.models.ImageModel

interface NetworkDataSource {
    suspend fun get(): List<ImageModel>

    suspend fun search(request: String): List<ImageModel>

}