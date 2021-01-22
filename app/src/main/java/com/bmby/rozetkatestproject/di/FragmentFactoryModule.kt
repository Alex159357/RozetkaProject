package com.bmby.rozetkatestproject.di

import androidx.fragment.app.FragmentFactory
import com.bmby.rozetkatestproject.ui.fragments.details.DetailsFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(ApplicationComponent::class)
object FragmentFactoryModule {

    @Singleton
    @Provides
    fun provideDetailsFragmentFactory(): FragmentFactory {
        return DetailsFragmentFactory()
    }

}