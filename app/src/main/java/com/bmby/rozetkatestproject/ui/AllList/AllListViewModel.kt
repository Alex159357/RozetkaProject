package com.bmby.rozetkatestproject.ui.AllList

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bmby.rozetkatestproject.data_state.DataState
import com.bmby.rozetkatestproject.ui.repository.all_pictures_repository.AllPicturesRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AllListViewModel
@ViewModelInject
constructor(
    private val allPicturesRepository: AllPicturesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private  val _dataState : MutableLiveData<DataState<String>> = MutableLiveData()

    val dataState: LiveData<DataState<String>> get() = _dataState

    fun setStateEvent(){
        viewModelScope.launch {
            allPicturesRepository.getTest().onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)
        }
    }

}