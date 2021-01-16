package com.bmby.rozetkatestproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageAdaptor
@Inject
constructor(private var images : List<ImageModel>) : RecyclerView.Adapter<ImageAdaptor.ViewHolder>() {


    fun addData(list: List<ImageModel>) {
        images = list
    }
    private var onClickListener: ItemListOnClickListener? = null

    fun setOnClickListener(onClickListener: ItemListOnClickListener) {
        this.onClickListener = onClickListener;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ImageAdaptor.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image: ImageModel = images[position]
        holder.ivListImage.setOnClickListener {
            onClickListener!!.onClick(image.id!!)
        }
        holder.ivListImage.load(image.urls!!.thumb){
            crossfade(false)
            transformations(RoundedCornersTransformation(5f))
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {
        val ivListImage: ImageView = view.findViewById(R.id.list_item_image)
    }

}

class ItemListOnClickListener(val clickListener: (itemID: String) -> Unit) {
    fun onClick(itemID: String) = clickListener(itemID)
}