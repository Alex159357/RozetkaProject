package com.bmby.rozetkatestproject.framework.network.retrofit

import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.SearchNetworkImageModel
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageRetrofit {

    @GET("photos")
    suspend fun get(
        @Query("client_id") client_id: String,
        @Query("per_page") size: Int,
        @Query("page") page : Int
    ): List<ImageNetworkEntity>

    @GET("search/photos")
    suspend fun search(
        @Query("client_id") client_id: String,
        @Query("query") query: String,
        @Query("page") page : Int
    ): SearchNetworkImageModel

    @GET("photos/{id}")
    suspend fun getById(
        @Path("id") id: String,
        @Query("client_id") client_id: String,
        @Query("page") page : Int
    ): ImageNetworkEntity


}