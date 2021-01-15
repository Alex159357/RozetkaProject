package com.bmby.rozetkatestproject.di

import com.bmby.rozetkatestproject.framework.network.ImageRetrofitService
import com.bmby.rozetkatestproject.framework.network.ImageRetrofitServiceImpl
import com.bmby.rozetkatestproject.framework.network.mappers.NetworkMapper
import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.retrofit.ImageRetrofit
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import com.bmby.rozetkatestproject.logic.network.NetworkDataSource
import com.bmby.rozetkatestproject.logic.network.NetworkDataSourceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkMapper(): EntityMapper<ImageNetworkEntity, ImageModel> {
        return NetworkMapper()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com//")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }


    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): ImageRetrofit {
        return retrofit
            .build()
            .create(ImageRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        blogRetrofit: ImageRetrofit
    ): ImageRetrofitService {
        return ImageRetrofitServiceImpl(blogRetrofit)
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(
        blogRetrofitService: ImageRetrofitService,
        networkMapper: NetworkMapper
    ): NetworkDataSource {
        return NetworkDataSourceImpl(blogRetrofitService, networkMapper)
    }


}