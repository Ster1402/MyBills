package com.sterdevs.mybills.data.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.R

data class HomeItem(val name: String, val address: String)
class HomeAdapter(private val homeItemList: List<HomeItem>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val homeItem = homeItemList[position]
        holder.bind(homeItem)
    }

    override fun getItemCount(): Int {
        return homeItemList.size
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.home_name_ref)
        private val addressTextView: TextView = itemView.findViewById(R.id.location_name)

        fun bind(homeItem: HomeItem) {
            nameTextView.text = homeItem.name
            addressTextView.text = homeItem.address
        }
    }
}