package com.bmby.rozetkatestproject.ui.fragments.details

import android.Manifest
import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import com.bmby.rozetkatestproject.logic.domain.models.ImagesUserModel
import com.bmby.rozetkatestproject.logic.domain.models.UserProfileImage
import com.bmby.rozetkatestproject.logic.domain.states.DataState
import com.bmby.rozetkatestproject.logic.domain.states.ImageDownloadState
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream


@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailsFragment constructor(
    private val id: String?
) : Fragment(R.layout.details_fragment), View.OnClickListener {

    private lateinit var ll_loading_bar: LinearLayout
    private lateinit var llPicInfo: LinearLayout
    private lateinit var llDownloadImageLayout: LinearLayout
    private lateinit var detailCaption: TextView
    private lateinit var ivDetailImage: ImageView
    private lateinit var btnDownload: LinearLayout
    private lateinit var llShare: LinearLayout
    private lateinit var imageModel: ImageModel
    private lateinit var tvUserName: TextView
    private lateinit var ivDownload: ImageView
    private lateinit var ivUserImg : ImageView
    private lateinit var tvDownloadState: TextView
    private lateinit var tvUserLocation : TextView
    private lateinit var tvImageDescription : TextView
    private lateinit var tvLikeCount : TextView
    private val viewModel: DetailsViewModel by viewModels()
    private val permissionsList = listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray()
    private val MY_PERMISSIONS_ERQUEST = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        askPermissions()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
        setUpComponents()
        viewModel.setStateEvent(id!!)
        subscribeObservers()
    }


    private fun subscribeObservers() {
        //Load image data
        viewModel.dataState.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success -> displayData(it.data)
                is DataState.Error -> displayError(it.exception.message!!)
                DataState.Loading -> loading(true)
            }
        })
        //Download image state
        viewModel.downloadDataState.observe(viewLifecycleOwner, {
            when(it){
                is ImageDownloadState.Success -> downloadingMessages("Download successfully")
                ImageDownloadState.Fail -> downloadingMessages("Download fail, try more")
                ImageDownloadState.Paused -> downloadImageStatus(true)
                ImageDownloadState.Pending -> downloadImageStatus(true)
                ImageDownloadState.Running -> downloadImageStatus(true)
                is ImageDownloadState.Error -> downloadingMessages(it.exception.message!!)
            }

        })
    }

    private fun initialize(view: View) {
        with(view) {
            ivDetailImage = findViewById(R.id.ivDetailImage)
            ll_loading_bar = findViewById(R.id.ll_loading_bar)
            detailCaption = findViewById(R.id.detailCaption)
            btnDownload = findViewById(R.id.btnDownload)
            llShare = findViewById(R.id.llShare)
            ivDownload = findViewById(R.id.ivDownload)
            tvDownloadState = findViewById(R.id.tvDownloadState)
            ivUserImg = findViewById(R.id.ivUserImg)
            tvUserName = findViewById(R.id.tvUserName)
            tvUserLocation = findViewById(R.id.tvUserLocation)
            tvLikeCount = findViewById(R.id.tvLikeCount)
            tvImageDescription = findViewById(R.id.tvImageDescription)
            llPicInfo = findViewById(R.id.llPicInfo)
            llDownloadImageLayout = findViewById(R.id.llDownloadImageLayout)
        }
    }

    private fun setUpComponents(){
        llShare.setOnClickListener(this)
        btnDownload.setOnClickListener(this)
    }

    private fun displayData(data: ImageModel) {
        imageModel = data
        displayImage(imageModel)
        displayProfileData(imageModel.user!!)
        tvLikeCount.text = imageModel.likes.toString()
        tvImageDescription.text = imageModel.description
    }

    private fun displayImage(imageModel: ImageModel){
        ivDetailImage.load(imageModel.urls!!.regular){
            target(object : coil.target.Target {
                override fun onSuccess(result: Drawable) {
                    super.onSuccess(result)
                    llPicInfo.visibility = View.VISIBLE
                    ivDetailImage.setImageDrawable(result)
                    loading(false)
                }
                override fun onStart(placeholder: Drawable?) {
                    loading(true)
                    super.onStart(placeholder)
                }
                override fun onError(error: Drawable?) {
                    llPicInfo.visibility = View.GONE
                    super.onError(error)
                }
            })
        }
    }

    private fun displayProfileData(user: ImagesUserModel){
        ivUserImg.load(user.profile_image!!.medium) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_account_circle_24)
            transformations(CircleCropTransformation())
        }
        tvUserName.text = user.name
        tvUserLocation.text = user.location

    }

    private fun displayError(e: String) {
        loading(false)
        detailCaption.visibility = View.VISIBLE
        detailCaption.text = e
    }

    private fun loading(isDisplayed: Boolean) {
        ll_loading_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun share() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, imageModel.urls!!.full)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        requireActivity().startActivity(shareIntent)
    }

    private fun downloadImage() {
        if(askPermissions())
            viewModel.downloadImage(imageModel)
    }


    private fun askPermissions() : Boolean{
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(),
                permissionsList, MY_PERMISSIONS_ERQUEST)
        }else{
            return true
        }
        return false
    }

    private fun downloadingMessages(msg: String){
        downloadImageStatus(false)
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    private fun downloadImageStatus(isLoading: Boolean){
        llDownloadImageLayout.visibility = if(!isLoading) View.GONE else View.VISIBLE
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.llShare -> share()
            R.id.btnDownload -> downloadImage()
        }
    }
}