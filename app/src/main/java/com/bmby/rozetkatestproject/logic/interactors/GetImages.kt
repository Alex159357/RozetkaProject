package com.bmby.rozetkatestproject.logic.interactors


import android.util.Log
import androidx.core.os.trace
import com.bmby.rozetkatestproject.logic.cache.CacheDataSource
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.logic.network.NetworkDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception

class GetImages constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
) {


    // Access Key  ytX0jueCagugbalpOGNdM-Rf26c1rStBFW4WcXhV1rE
    // Secret Key  tYk1I2_EOkgKIs6oXhTZpubLXfNskZ5I-o9LufCt-e0

    suspend fun getTest(): Flow<DataState<List<ImageModel>>> = flow {
        try {
            emit(DataState.Loading)
//            delay(5000)
//            val serverResult = networkDataSource.search("London")
            val serverResult = networkDataSource.get()
            Log.e("Loaded ", serverResult.size.toString())
            emit(DataState.Success(serverResult))
        } catch (e: IOException) {
            e.stackTrace
            emit(DataState.Error(e))
        }
    }

}