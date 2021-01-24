package com.bmby.rozetkatestproject.di

import android.content.Context
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.framework.network.ImageRetrofitService
import com.bmby.rozetkatestproject.framework.network.ImageRetrofitServiceImpl
import com.bmby.rozetkatestproject.framework.network.downloader.ImageDownloader
import com.bmby.rozetkatestproject.framework.network.mappers.NetworkMapper
import com.bmby.rozetkatestproject.framework.network.mappers.ProfilePicturesMapper
import com.bmby.rozetkatestproject.framework.network.mappers.UrlsMapper
import com.bmby.rozetkatestproject.framework.network.mappers.UserMapper
import com.bmby.rozetkatestproject.framework.network.models.ImageNetworkEntity
import com.bmby.rozetkatestproject.framework.network.models.ImageUrlsNetworkModel
import com.bmby.rozetkatestproject.framework.network.models.ImageUserNetworkModel
import com.bmby.rozetkatestproject.framework.network.models.UserProfilePicturesNetwork
import com.bmby.rozetkatestproject.framework.network.retrofit.ImageRetrofit
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageUrlsModel
import com.bmby.rozetkatestproject.logic.domain.models.ImagesUserModel
import com.bmby.rozetkatestproject.logic.domain.models.UserProfileImage
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import com.bmby.rozetkatestproject.logic.network.NetworkDataSource
import com.bmby.rozetkatestproject.logic.network.NetworkDataSourceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideUrlsMapper(): EntityMapper<ImageUrlsNetworkModel, ImageUrlsModel> {
        return UrlsMapper()
    }

    @Singleton
    @Provides
    fun provideProfilePicturesMapper(): EntityMapper<UserProfilePicturesNetwork, UserProfileImage> {
        return ProfilePicturesMapper()
    }

    @Singleton
    @Provides
    fun provideUserMapper(profilePicturesMapper: ProfilePicturesMapper): EntityMapper<ImageUserNetworkModel, ImagesUserModel> {
        return UserMapper(profilePicturesMapper)
    }

    @Singleton
    @Provides
    fun provideNetworkMapper(
        urlsMapper: UrlsMapper,
        userMapper: UserMapper
    ): EntityMapper<ImageNetworkEntity, ImageModel> {
        return NetworkMapper(urlsMapper, userMapper)
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
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideDownloader(@ApplicationContext appContext: Context): ImageDownloader {
        return ImageDownloader(appContext)
    }

    @Singleton
    @Provides
    fun provideImageService(retrofit: Retrofit.Builder): ImageRetrofit {
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
        networkMapper: NetworkMapper,
        imageDownloader: ImageDownloader,
    ): NetworkDataSource {
        return NetworkDataSourceImpl(blogRetrofitService, networkMapper, imageDownloader)
    }

}