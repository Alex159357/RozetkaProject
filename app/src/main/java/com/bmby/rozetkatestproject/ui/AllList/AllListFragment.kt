package com.bmby.rozetkatestproject.ui.AllList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.data_state.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllListFragment : Fragment() {

    companion object {
        fun newInstance() = AllListFragment()
    }
    private lateinit var ll_loading_bar : LinearLayout
    private lateinit var testCaption: TextView
    private val viewModel: AllListViewModel by viewModels()
    private  var mView : View? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.all_list_fragment, container, false)
        initialize(mView!!)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
        viewModel.setStateEvent()
    }


    private fun initialize(view: View){
        ll_loading_bar = mView!!.findViewById(R.id.ll_loading_bar)
         testCaption = mView!!.findViewById(R.id.testCaption)
    }

    private fun subscribeObservers(){

        viewModel.dataState.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success ->displayData(it.data)
                is DataState.Error -> displayData(it.exception.message!!)
                DataState.Loading -> loading(true)
            }
        })
    }

    private fun displayData(data: String){
        testCaption.text = data
        loading(false)
    }

    private fun loading(isDisplayed : Boolean){
        ll_loading_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

}