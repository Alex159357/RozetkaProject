package com.bmby.rozetkatestproject.ui.fragments.saved

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.logic.interactors.GetImages
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


enum class SortState{
    NOTHING, BY_USER, BY_DATE
}

class SavedViewModel
@ViewModelInject
constructor(
    private val getImages: GetImages,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var sortState = SortState.NOTHING

    private val _dataState: MutableLiveData<DataState<List<ImageModel>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<ImageModel>>> get() = _dataState



    fun setStateEvent(){
        when(sortState){
            SortState.NOTHING -> getAll()
            SortState.BY_USER -> getByDate()
            SortState.BY_DATE -> getByUser()
        }
    }


     private fun getAll(){
        viewModelScope.launch {
            getImages.getListFromCache().onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)
        }
    }

     private fun getByDate(){
        viewModelScope.launch {
            getImages.getListFromCacheByDate().onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)
        }
    }


    private fun getByUser(){
        viewModelScope.launch {
            getImages.getListFromCacheByUser().onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)
        }
    }

}