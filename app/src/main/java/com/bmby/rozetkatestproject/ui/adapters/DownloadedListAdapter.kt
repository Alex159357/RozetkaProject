package com.bmby.rozetkatestproject.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.helpers.toDate
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DownloadedListAdapter
@Inject constructor(private var images : List<ImageModel>, private val context: Context) :
    RecyclerView.Adapter<DownloadedListAdapter.ViewHolder>(){

    fun addData(list: List<ImageModel>) {
        images = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return DownloadedListAdapter.ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.downloaded_image_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image: ImageModel = images[position]
        holder.ivImage.load(image.urls!!.thumb){
            crossfade(false)
            transformations(RoundedCornersTransformation(5f))
        }
        holder.tvSavedDate.text = image.savedDate.toDate
    }

    override fun getItemCount(): Int {
        return images.size
    }


    class ViewHolder constructor(private val view: View): RecyclerView.ViewHolder(view) {
        val ivImage = view.findViewById<ImageView>(R.id.ivImage)
        val tvSavedDate = view.findViewById<TextView>(R.id.tvSavedDate)
    }


}
