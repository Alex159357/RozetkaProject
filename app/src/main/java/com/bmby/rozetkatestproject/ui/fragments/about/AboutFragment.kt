package com.bmby.rozetkatestproject.ui.fragments.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebView
import androidx.fragment.app.viewModels
import com.bmby.rozetkatestproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : Fragment(R.layout.about_fragment) {

    lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
        setUpComponents()
    }


    private fun initialize(view: View){
        webView = view.findViewById(R.id.webView)
    }

    private fun setUpComponents(){
        webView.loadUrl("file:///android_asset/about.html");
    }


}