package com.bmby.rozetkatestproject.logic.interactors


import android.app.DownloadManager
import android.util.Log
import androidx.core.os.trace
import com.bmby.rozetkatestproject.logic.cache.CacheDataSource
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.logic.domain.states.ImageDownloadState
import com.bmby.rozetkatestproject.logic.network.NetworkDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.IOException
import java.lang.Exception

class GetImages constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
) {

    suspend fun downloadImagelist(): Flow<DataState<List<ImageModel>>> = flow {
        emit(DataState.Loading)
        try {
//            val serverResult = networkDataSource.search("London")
            val serverResult = networkDataSource.get()
            Log.e("Loaded ", serverResult.size.toString())
            emit(DataState.Success(serverResult))
        } catch (e: IOException) {
            e.stackTrace
            emit(DataState.Error(e))
        }
    }

    suspend fun searchImagelist(q: String): Flow<DataState<List<ImageModel>>> = flow {
        emit(DataState.Loading)
        try {
            val serverResult = networkDataSource.search(q)
            Log.e("Loaded ", serverResult.size.toString())
            emit(DataState.Success(serverResult))
        } catch (e: IOException) {
            e.stackTrace
            emit(DataState.Error(e))
        }
    }




    suspend fun downloadById(id:String) : Flow<DataState<ImageModel>> = flow {
        try{
            emit(DataState.Loading)
            val imageModel: ImageModel = networkDataSource.getById(id)
            emit(DataState.Success(imageModel))
        }catch (e: IOException){
            e.stackTrace
            emit(DataState.Error(e))
        }
    }


    suspend fun getListFromCache() : Flow<DataState<List<ImageModel>>> = flow {
        emit(DataState.Loading)
        try{
            val imageList = cacheDataSource.get()
            emit(DataState.Success(imageList))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }


    suspend fun getListFromCacheByDate() : Flow<DataState<List<ImageModel>>> = flow {
        emit(DataState.Loading)
        try{
            val imageList = cacheDataSource.getSortedByDate()
            emit(DataState.Success(imageList))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun getListFromCacheByUser() : Flow<DataState<List<ImageModel>>> = flow {
        emit(DataState.Loading)
        try{
            val imageList = cacheDataSource.getSortedByUser()
            emit(DataState.Success(imageList))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }


}