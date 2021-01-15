package com.bmby.rozetkatestproject.logic.interactors


import androidx.core.os.trace
import com.bmby.rozetkatestproject.logic.cache.CacheDataSource
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.logic.network.NetworkDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetImages constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
) {


    // Access Key  ytX0jueCagugbalpOGNdM-Rf26c1rStBFW4WcXhV1rE
    // Secret Key  tYk1I2_EOkgKIs6oXhTZpubLXfNskZ5I-o9LufCt-e0

    suspend fun getTest(): Flow<DataState<String>> = flow {
        try {
            emit(DataState.Loading)
//            delay(5000)
            val serverResult = networkDataSource.search("London")
            emit(DataState.Success("${serverResult.size}"))
        } catch (e: Exception) {
            e.stackTrace
            emit(DataState.Error(e))
        }

    }

}