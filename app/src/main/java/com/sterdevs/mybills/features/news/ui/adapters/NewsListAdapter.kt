package com.sterdevs.mybills.features.news.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.features.news.domain.models.News

class NewsListAdapter(val newsList: List<News>) : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {

    inner class NewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    /**
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        TODO("Not yet implemented")
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int = newsList.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}