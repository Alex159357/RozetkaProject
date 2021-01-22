package com.bmby.rozetkatestproject.ui.fragments.details

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.logic.domain.states.ImageDownloadState
import com.bmby.rozetkatestproject.logic.interactors.GetImages
import com.bmby.rozetkatestproject.logic.interactors.SaveImage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailsViewModel
@ViewModelInject
constructor(
    private val getImages: GetImages,
    private val saveImage: SaveImage,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState : MutableLiveData<DataState<ImageModel>> = MutableLiveData()
    private val _downloadDataState: MutableLiveData<ImageDownloadState<String>> = MutableLiveData()

    val dataState: LiveData<DataState<ImageModel>> get() = _dataState
    val downloadDataState: LiveData<ImageDownloadState<String>> = _downloadDataState


    fun setStateEvent(id: String){
        viewModelScope.launch {
            getImages.getById(id).onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun downloadImage(url: String){
        viewModelScope.launch {
            saveImage.downloadImage(url).onEach {
                _downloadDataState.value = it
            }.launchIn(viewModelScope)
        }
    }

}