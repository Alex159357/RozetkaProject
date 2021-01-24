package com.bmby.rozetkatestproject.ui.fragments.saved

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.logic.interactors.GetImages
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class SavedViewModel
@ViewModelInject
constructor(
    private val getImages: GetImages,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _dataState: MutableLiveData<DataState<List<ImageModel>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<ImageModel>>> get() = _dataState


    fun setStateEvent(){
        viewModelScope.launch {
            getImages.getListFromCache().onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)
        }
    }


}