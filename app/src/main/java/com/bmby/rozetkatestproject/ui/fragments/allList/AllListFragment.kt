package com.bmby.rozetkatestproject.ui.fragments.allList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.ui.activities.image_details.ImageDetailsActivity
import com.bmby.rozetkatestproject.ui.adapters.ImageAdaptor
import com.bmby.rozetkatestproject.ui.adapters.ItemListOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AllListFragment : Fragment(R.layout.all_list_fragment), TextView.OnEditorActionListener, View.OnClickListener {

    private lateinit var ll_loading_bar: LinearLayout
    private lateinit var testCaption: TextView
    private lateinit var rvImages: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var ibntSearch: ImageButton
    private lateinit var imageAdapter: ImageAdaptor
    private var imagesList: List<ImageModel> = listOf()
    private var rcViewState: Parcelable? = null
    private val viewModel: AllListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            rcViewState = savedInstanceState.getParcelable("rvState")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
        setUpElement()
        setupRecyclerView()
        subscribeObservers()
        viewModel.setStateEvent()
    }

    private fun initialize(view: View) {
        with(view) {
            ll_loading_bar = findViewById(R.id.ll_loading_bar)
            testCaption = findViewById(R.id.tvAllListCation)
            rvImages = findViewById(R.id.rvImages)
            etSearch = findViewById(R.id.etSearch)
            ibntSearch= findViewById(R.id.ibntSearch)
        }
    }


    private fun setUpElement(){
        etSearch.setOnEditorActionListener(this)
        ibntSearch.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        imageAdapter = ImageAdaptor(imagesList, requireContext())
        rvImages.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = imageAdapter
        }

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success -> displayData(it.data)
                is DataState.Error -> displayError(it.exception.message!!)
                DataState.Loading -> loading(true)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::rvImages.isInitialized)
            outState.putParcelable("rvState", rcViewState)
    }

    override fun onPause() {
        super.onPause()
        if (::rvImages.isInitialized)
            rcViewState = rvImages.layoutManager!!.onSaveInstanceState()
    }

    private fun displayData(data: List<ImageModel>) {
        if(data.isEmpty()){
            displayError("No data found")
        }
        imageAdapter.addData(data)
        imagesList = data
        imageAdapter.notifyDataSetChanged()
        if (rcViewState != null)
            rvImages.layoutManager!!.onRestoreInstanceState(rcViewState);
        loading(false)
        imageAdapter.setOnClickListener(ItemListOnClickListener {
            showDetails(it)
        })

    }

    private fun showDetails(id: String) {
        val intent = Intent(context, ImageDetailsActivity::class.java)
        intent.putExtra("item_id", id)
        startActivity(intent)
    }

    private fun displayError(e: String) {
        testCaption.visibility = View.VISIBLE
        testCaption.text = e
    }

    private fun loading(isDisplayed: Boolean) {
        ll_loading_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            search()
            return true
        }
        return false
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ibntSearch -> search()
        }
    }

    private fun search(){
        val inputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
        var request: String = etSearch.text.toString()
        if(request.isNotEmpty()){
            viewModel.setStateEventSearch(request)
        }else viewModel.setStateEvent()
        Log.e("SearchIn", request)
    }

}