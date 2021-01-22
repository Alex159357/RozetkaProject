package com.bmby.rozetkatestproject.ui.activities.image_details

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentFactory
import androidx.viewpager.widget.ViewPager
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.ui.fragments.details.DetailsFragment
import com.bmby.rozetkatestproject.ui.fragments.details.DetailsFragmentFactory
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import java.security.Permission
import javax.inject.Inject

@AndroidEntryPoint
class ImageDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: DetailsFragmentFactory

    private val permissionsList = listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray()
    private val MY_PERMISSIONS_ERQUEST = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        supportActionBar!!.hide()
        if (ContextCompat.checkSelfPermission(
                this@ImageDetailsActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this@ImageDetailsActivity,
                permissionsList, MY_PERMISSIONS_ERQUEST)
        }else{
            goToDetails(true)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == MY_PERMISSIONS_ERQUEST && permissions.contentEquals(permissionsList)){
            goToDetails(true)
        }else goToDetails(false)
    }


    private fun goToDetails(allowSaveFile: Boolean) {
        fragmentFactory.id = intent.extras?.getString("item_id").toString()
        fragmentFactory.allowSaveFiles = allowSaveFile
        supportFragmentManager.fragmentFactory = fragmentFactory
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DetailsFragment::class.java, null)
            .commit()
    }

}