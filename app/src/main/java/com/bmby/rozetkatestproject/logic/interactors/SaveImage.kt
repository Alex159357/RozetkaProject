package com.bmby.rozetkatestproject.logic.interactors

import android.app.DownloadManager
import android.util.Log
import com.bmby.rozetkatestproject.logic.cache.CacheDataSource
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.ImageDownloadState
import com.bmby.rozetkatestproject.logic.network.NetworkDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.IOException

class SaveImage constructor(
    private val networkDataSource: NetworkDataSource,
    private val cacheDataSource: CacheDataSource
) {

    suspend fun downloadImage(imageModel: ImageModel): Flow<ImageDownloadState<Nothing>> = flow {
        emit(ImageDownloadState.Pending)
        delay(100)
        try {
            when (networkDataSource.downloadImage(imageModel.urls!!.full!!)) {
                DownloadManager.STATUS_FAILED -> emit(ImageDownloadState.Fail)
                DownloadManager.STATUS_PAUSED -> emit(ImageDownloadState.Paused)
                DownloadManager.STATUS_PENDING -> {
                    emit(ImageDownloadState.Pending)
                    delay(100)
                }
                DownloadManager.STATUS_RUNNING -> {
                    emit(ImageDownloadState.Running)
                    delay(100)
                }
                DownloadManager.STATUS_SUCCESSFUL -> {
                    emit(ImageDownloadState.Success)
                }
                else -> emit(ImageDownloadState.Fail)
            }
            cacheDataSource.insert(imageModel)
            val count = cacheDataSource.get().size

            Log.e("InDb", "$count")

        } catch (e: IOException) {
            emit(ImageDownloadState.Error(e))
        }
    }


}