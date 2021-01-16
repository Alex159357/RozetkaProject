package com.bmby.rozetkatestproject.ui.AllList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.ui.adapters.ImageAdaptor
import com.bmby.rozetkatestproject.ui.adapters.ItemListOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllListFragment : Fragment() {

    companion object {
        fun newInstance() = AllListFragment()
    }
    private lateinit var ll_loading_bar : LinearLayout
    private lateinit var testCaption: TextView
    private val viewModel: AllListViewModel by viewModels()
    private  var mView : View? = null
    private lateinit var rvImages : RecyclerView
   private lateinit var imageAdapter :  ImageAdaptor
   private var imagesList : List<ImageModel>  = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.all_list_fragment, container, false)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
        viewModel.setStateEvent()
        initialize(mView!!)
        rvImages.layoutManager = GridLayoutManager(requireView().context, 1)
    }


    private fun initialize(view: View){
        ll_loading_bar = mView!!.findViewById(R.id.ll_loading_bar)
         testCaption = mView!!.findViewById(R.id.testCaption)
        rvImages = mView!!.findViewById(R.id.rvImages)
        imageAdapter = ImageAdaptor(imagesList)
        rvImages.adapter = imageAdapter

    }

    private fun subscribeObservers(){

        viewModel.dataState.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success ->displayData(it.data)
                is DataState.Error -> displayError(it.exception.message!!)
                DataState.Loading -> loading(true)
            }
        })
    }

    private fun displayData(data: List<ImageModel>){
        imageAdapter.addData(data)
        imageAdapter.notifyDataSetChanged()
        loading(false)
        imageAdapter.setOnClickListener(ItemListOnClickListener {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

    }

    private fun displayError(e: String){
        testCaption.visibility = View.VISIBLE
        testCaption.text = e
    }

    private fun loading(isDisplayed : Boolean){
        ll_loading_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

}