package com.bmby.rozetkatestproject.logic.interactors

import android.app.DownloadManager
import com.bmby.rozetkatestproject.logic.domain.states.ImageDownloadState
import com.bmby.rozetkatestproject.logic.network.NetworkDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.IOException

class SaveImage constructor(private val networkDataSource: NetworkDataSource) {


    suspend fun downloadImage(url: String): Flow<ImageDownloadState<Nothing>> = flow {
        emit(ImageDownloadState.Running)
        delay(1000)
        try {
            when (networkDataSource.downloadImage(url)) {
                DownloadManager.STATUS_FAILED -> emit(ImageDownloadState.Fail)
                DownloadManager.STATUS_PAUSED -> emit(ImageDownloadState.Paused)
                DownloadManager.STATUS_PENDING -> emit(ImageDownloadState.Pending)
                DownloadManager.STATUS_RUNNING -> emit(ImageDownloadState.Running)
                DownloadManager.STATUS_SUCCESSFUL -> {
                    delay(1000)
                    emit(ImageDownloadState.Success)
                }
                else -> emit(ImageDownloadState.Fail)
            }
        } catch (e: IOException) {
            emit(ImageDownloadState.Error(e))
        }
    }

}