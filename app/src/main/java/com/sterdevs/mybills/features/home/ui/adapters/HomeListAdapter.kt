package com.sterdevs.mybills.features.home.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.commons.core.models.Home

class HomeListAdapter(var homes: MutableList<Home>) : RecyclerView.Adapter<HomeListAdapter.HomeViewHolder>() {
    class HomeViewHolder(private val itemView : View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = homes.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}