package com.bmby.rozetkatestproject.ui.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.bmby.rozetkatestproject.R
import com.bmby.rozetkatestproject.logic.domain.models.ImageModel
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageAdaptor
@Inject
constructor(private var images : List<ImageModel>, private val context: Context) : RecyclerView.Adapter<ImageAdaptor.ViewHolder>() {


    fun addData(list: List<ImageModel>) {
        images = list
    }
    private var onClickListener: ItemListOnClickListener? = null

    fun setOnClickListener(onClickListener: ItemListOnClickListener) {
        this.onClickListener = onClickListener;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ImageAdaptor.ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false)
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
        holder.ivUserPic.load(image.user!!.profile_image.small) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_account_circle_24)
            transformations(CircleCropTransformation())

        }
        holder.tvUserName.text = image.user!!.name
        holder.tvLikes.text = image.likes.toString()
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {
        val ivListImage: ImageView = view.findViewById(R.id.list_item_image)
        val ivUserPic : ImageView = view.findViewById(R.id.ivUserPic)
        val tvUserName: TextView = view.findViewById(R.id.tvUserName)
        val tvLikes: TextView = view.findViewById(R.id.tvLikes)
    }

}

class ItemListOnClickListener(val clickListener: (itemID: String) -> Unit) {
    fun onClick(itemID: String) = clickListener(itemID)
}