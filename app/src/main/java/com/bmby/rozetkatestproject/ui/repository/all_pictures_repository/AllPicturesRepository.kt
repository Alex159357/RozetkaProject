package com.bmby.rozetkatestproject.ui.repository.all_pictures_repository

import androidx.compose.runtime.emit
import com.bmby.rozetkatestproject.data_state.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class AllPicturesRepository {

    suspend fun getTest(): Flow<DataState<String>> = flow {
        try {
            emit(DataState.Loading)
            delay(5000)
            emit(DataState.Success("Data state result from repository"))
        }catch (e : Exception){
            emit(DataState.Error(e))
        }

    }

}