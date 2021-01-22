package com.bmby.rozetkatestproject.di

import android.content.Context
import com.bmby.rozetkatestproject.framework.network.downloader.ImageDownloader
import com.bmby.rozetkatestproject.logic.interactors.SaveImage
import com.bmby.rozetkatestproject.logic.network.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DownloaderModule {

    @Singleton
    @Provides
    fun provideSaveImage(networkDataSource: NetworkDataSource): SaveImage{
        return SaveImage(networkDataSource)
    }

}