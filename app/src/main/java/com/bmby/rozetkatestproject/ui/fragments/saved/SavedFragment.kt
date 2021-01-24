package com.bmby.rozetkatestproject.ui.fragments.saved

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.saved_fragment) {

    private val viewModel: SavedViewModel by viewModels()
    private lateinit var ll_loading_bar: LinearLayout
    private lateinit var tvCaption: TextView
    private lateinit var rvSavedImages: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
        viewModel.setStateEvent()
        subscribeObservers()
    }

    //TODO remove search fragments and add about fragment.

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success -> displayData(it.data)
                is DataState.Error -> displayError(it.exception.message!!)
                DataState.Loading -> loading(true)
            }
        })
    }

    private fun initialize(view: View){
        with(view){
            ll_loading_bar = findViewById(R.id.ll_loading_bar)
            tvCaption = findViewById(R.id.tvSavedListCaption)
            rvSavedImages = findViewById(R.id.rvSavedImages)
        }
    }

    private fun loading(b: Boolean) {
        Log.e("ShowFromDb", "Loading is -> $b")
    }

    private fun displayError(message: String) {
        Log.e("ShowFromDb", "Error -> $message")
    }

    private fun displayData(data: List<ImageModel>) {
        Log.e("ShowFromDb", "Data loaded -> ${data.size}")
    }


}