package com.bmby.rozetkatestproject.di

import android.content.Context
import androidx.room.Room
import com.bmby.rozetkatestproject.framework.cache.ImageDaoServiceImpl
import com.bmby.rozetkatestproject.framework.cache.ImagesDaoService
import com.bmby.rozetkatestproject.framework.cache.database.ImagesDao
import com.bmby.rozetkatestproject.framework.cache.database.ImagesDatabase
import com.bmby.rozetkatestproject.framework.cache.mappers.CacheMapper
import com.bmby.rozetkatestproject.framework.cache.models.ImagesCacheEntity
import com.bmby.rozetkatestproject.logic.cache.CacheDataSource
import com.bmby.rozetkatestproject.logic.cache.CacheDataSourceImpl
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<ImagesCacheEntity, ImageModel> {
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): ImagesDatabase {
        return Room
            .databaseBuilder(
                context,
                ImagesDatabase::class.java,
                ImagesDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: ImagesDatabase): ImagesDao {
        return blogDatabase.blogDao()
    }

    @Singleton
    @Provides
    fun provideBlogDaoService(
        blogDao: ImagesDao
    ):ImagesDaoService{
        return ImageDaoServiceImpl(blogDao)
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(
        imageDaoService: ImagesDaoService,
        cacheMapper: CacheMapper
    ): CacheDataSource {
        return CacheDataSourceImpl(imageDaoService, cacheMapper)
    }

}