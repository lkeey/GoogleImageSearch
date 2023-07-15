package com.aleshka.firstkotlinapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.aleshka.firstkotlinapp.R
import com.aleshka.firstkotlinapp.data.interfaces.ImgClickedListener
import com.aleshka.firstkotlinapp.data.models.GoogleItem

class GoogleImageAdapter(
    private val images : List<GoogleItem>,
    private val listener : ImgClickedListener
) : RecyclerView.Adapter<GoogleImageAdapter.ImgViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.img_item, parent, false)

        return ImgViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        val currentItem = images[position]

        holder.setData(currentItem, listener)
    }

    class ImgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val img: ImageView = itemView.findViewById(R.id.img)
        private val url: TextView = itemView.findViewById(R.id.url)

        fun setData(item: GoogleItem, listener: ImgClickedListener) {

            // set data
            val strURL: String = item.image.url

            setImgUI(strURL)

            url.text = strURL

            // set listener
            itemView.setOnClickListener {listener.onItemClicked(item)}

        }

        private fun setImg(url: String, context: Context) {
            // in Background
            val request = ImageRequest.Builder(context)
                .data(url)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_loading)
                .target(img)
                .crossfade(1000)
                .crossfade(true)
                .transformations(RoundedCornersTransformation(40f))
                .build()

//            GlobalScope.launch {
//                imageLoading.execute(request)
//            }
        }

        private fun setImgUI(url: String) {
            // in UI thread
            img.load(url) {
                placeholder(R.drawable.ic_loading)
                error(R.drawable.ic_launcher_background)
                memoryCachePolicy(CachePolicy.ENABLED)
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(40f))
            }
        }

    }
}
