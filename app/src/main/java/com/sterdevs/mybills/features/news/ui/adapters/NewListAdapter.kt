package com.sterdevs.mybills.features.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.databinding.ViewItemHomeNewsBinding
import com.sterdevs.mybills.features.news.domain.models.New

class NewListAdapter(override val childRecyclerViewId: Int) :
    RecyclerView.Adapter<NewListAdapter.NewListViewHolder>(),
    NewListViewAdapter.OnChildItemClickListener {
    private val new: MutableList<New> = generateNews()
    private val childRecyclerViewButtonVisibility: MutableMap<Int, Boolean> = mutableMapOf()
    inner class NewListViewHolder(val binding: ViewItemHomeNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentNews = new[position]
                    binding.viewItemHomeNewsClearMessageButton.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewListAdapter.NewListViewHolder {
        val binding =
            ViewItemHomeNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewListViewHolder(binding)

    }

    override fun getItemCount(): Int = new.size

    override fun onBindViewHolder(holder: NewListAdapter.NewListViewHolder, position: Int) {
        val newItem = new[position]
        holder.binding.viewItemHomeNewsTitleText.text = newItem.cityName
        val newListViewAdapter = NewListViewAdapter(childRecyclerViewId,this)
        holder.binding.homeNewsRecyclerView.adapter = newListViewAdapter

        val isButtonVisible = childRecyclerViewButtonVisibility[childRecyclerViewId] ?: false

        if (isButtonVisible){
            holder.binding.viewItemHomeNewsClearMessageButton.visibility = View.VISIBLE
        } else {
            holder.binding.viewItemHomeNewsClearMessageButton.visibility = View.GONE
        }
    }

    fun generateNews(): MutableList<New> {
        val items = mutableListOf<New>()
        items.add(
            New("BIG BEN CITY")
        )
        items.add(
            New("CHICAGO CITY")
        )
        return items
    }

    override fun onChildItemClick(childRecyclerViewId: Int) {
        childRecyclerViewButtonVisibility[childRecyclerViewId] = true
        notifyDataSetChanged()
    }
}