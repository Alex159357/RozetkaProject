package com.bmby.rozetkatestproject.logic.domain.states

sealed class ImageDownloadState <out R>{

    object Success : ImageDownloadState<Nothing>()
    object  Fail : ImageDownloadState<Nothing>()
    object Paused : ImageDownloadState<Nothing>()
    object Pending : ImageDownloadState<Nothing>()
    object Running: ImageDownloadState<Nothing>()
    data class Error(val exception: Exception) : ImageDownloadState<Nothing>()

}