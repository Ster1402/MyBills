package com.sterdevs.mybills.commons.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.MaskableFrameLayout
import com.sterdevs.mybills.R

class CarouselImagesAdapter(var images: List<Int>) : RecyclerView.Adapter<CarouselImagesAdapter.CarouselImageViewHolder>() {

    class CarouselImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: MaskableFrameLayout = itemView.findViewById(R.id.carousel_item_container)
        val image: ImageView = itemView.findViewById(R.id.carousel_item_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselImageViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_carousel, parent, false)

        return CarouselImageViewHolder(itemView)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: CarouselImageViewHolder, position: Int) {
        holder.run { image.setImageResource(images[position]) }
    }


}