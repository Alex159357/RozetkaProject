package com.bmby.rozetkatestproject.ui.fragments.saved

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.ui.adapters.DownloadedListAdapter
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.saved_fragment), View.OnClickListener {

    private val viewModel: SavedViewModel by viewModels()
    private lateinit var ll_loading_bar: LinearLayout
    private lateinit var tvCaption: TextView
    private lateinit var rvSavedImages: RecyclerView
    private lateinit var llOrderByUser: LinearLayout
    private lateinit var llOrderByDate: LinearLayout
    private lateinit var llOrderClear: LinearLayout
    private var rcViewState: Parcelable? = null
    private lateinit var downloadedListAdapter: DownloadedListAdapter
    private var imagesList: List<ImageModel> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            rcViewState = savedInstanceState.getParcelable("rvState")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
        setButtonState()
        setUpElements()
        setupRecyclerView()
        viewModel.setStateEvent()
        subscribeObservers()
    }


    private fun setUpElements() {
        llOrderByUser.setOnClickListener(this)
        llOrderByDate.setOnClickListener(this)
        llOrderClear.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        val spanCount = if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2
        downloadedListAdapter = DownloadedListAdapter(imagesList, requireContext())
        rvSavedImages.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(
                spanCount,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = downloadedListAdapter
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

    private fun initialize(view: View) {
        with(view) {
            ll_loading_bar = findViewById(R.id.ll_loading_bar)
            tvCaption = findViewById(R.id.tvSavedListCaption)
            rvSavedImages = findViewById(R.id.rvSavedImages)
            llOrderByUser = findViewById(R.id.llOrderByUser)
            llOrderByDate = findViewById(R.id.llOrderByDate)
            llOrderClear = findViewById(R.id.llOrderClear)
        }
    }

    private fun loading(isDisplayed: Boolean) {
        ll_loading_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(message: String) {
        tvCaption.text = message
        loading(false)
    }

    private fun displayData(data: List<ImageModel>) {
        if(data.isEmpty()){
            displayError("You do not saved any thing yet!")
        }
        downloadedListAdapter.addData(data)
        imagesList = data
        downloadedListAdapter.notifyDataSetChanged()
        if (rcViewState != null)
            rvSavedImages.layoutManager!!.onRestoreInstanceState(rcViewState);
        loading(false)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.llOrderByUser -> sortByUser()
            R.id.llOrderByDate -> sortByDate()
            R.id.llOrderClear -> clearFilter()
        }
    }

    private fun sortByUser() {
        viewModel.sortState = SortState.BY_USER
        viewModel.setStateEvent()
        setButtonState()
    }

    private fun sortByDate() {
        viewModel.sortState = SortState.BY_DATE
        viewModel.setStateEvent()
        setButtonState()
    }

    private fun clearFilter() {
        viewModel.sortState = SortState.NOTHING
        viewModel.setStateEvent()
        setButtonState()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setButtonState() {
        when (viewModel.sortState) {
            SortState.NOTHING -> {
                llOrderByUser.background = resources.getDrawable(R.drawable.filter_item_shape)
                llOrderByDate.background = resources.getDrawable(R.drawable.filter_item_shape)
            }
            SortState.BY_USER -> {
                llOrderByUser.background =
                    resources.getDrawable(R.drawable.filter_item_shape_selected)
                llOrderByDate.background = resources.getDrawable(R.drawable.filter_item_shape)
            }
            SortState.BY_DATE -> {
                llOrderByDate.background =
                    resources.getDrawable(R.drawable.filter_item_shape_selected)
                llOrderByUser.background = resources.getDrawable(R.drawable.filter_item_shape)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::rvSavedImages.isInitialized)
            outState.putParcelable("rvState", rcViewState)
    }

    override fun onPause() {
        super.onPause()
        if (::rvSavedImages.isInitialized)
            rcViewState = rvSavedImages.layoutManager!!.onSaveInstanceState()
    }

}