package com.bmby.rozetkatestproject.di

import com.bmby.rozetkatestproject.ui.repository.all_pictures_repository.AllPicturesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AllPicturesRepositoryModule {

    @Singleton
    @Provides
    fun provideAllPicturesRepository(): AllPicturesRepository{
        return AllPicturesRepository()
    }

}