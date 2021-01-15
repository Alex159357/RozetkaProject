package com.bmby.rozetkatestproject.di


import com.bmby.rozetkatestproject.logic.cache.CacheDataSource
import com.bmby.rozetkatestproject.logic.interactors.GetImages
import com.bmby.rozetkatestproject.logic.network.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object GetImagesModule {

    @Singleton
    @Provides
    fun provideAllPicturesRepository(
        cacheDataSource: CacheDataSource,
        networkDataSource: NetworkDataSource
    ): GetImages {
        return GetImages(cacheDataSource, networkDataSource)
    }

}