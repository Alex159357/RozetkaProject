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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        supportActionBar!!.hide()
        fragmentFactory.id = intent.extras?.getString("item_id").toString()
        supportFragmentManager.fragmentFactory = fragmentFactory
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DetailsFragment::class.java, null)
            .commit()
    }


}