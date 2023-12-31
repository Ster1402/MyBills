package com.sterdevs.mybills.features.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.databinding.ViewItemHomeNewsBinding
import com.sterdevs.mybills.features.news.domain.models.New

class NewListAdapter(override val childRecyclerViewId: Int) :
    RecyclerView.Adapter<NewListAdapter.NewListViewHolder>(),
    MessageListAdapter.OnChildItemClickListener {
    private val messages: MutableList<New> = generateNews()
    private val childRecyclerViewButtonVisibility: MutableMap<Int, Boolean> = mutableMapOf()

    inner class NewListViewHolder(val binding: ViewItemHomeNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    if (binding.viewItemHomeNewsClearMessageButton.isVisible) {
                        binding.viewItemHomeNewsClearMessageButton.visibility = View.GONE
                        binding.checkBox.visibility = View.GONE
                    } else {
                        binding.viewItemHomeNewsClearMessageButton.visibility = View.VISIBLE
                        binding.checkBox.visibility = View.VISIBLE
                    }

                    binding.checkBox.isChecked =
                        binding.viewItemHomeNewsClearMessageButton.isVisible
                }

                true
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

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: NewListAdapter.NewListViewHolder, position: Int) {
        val newItem = messages[position]
        holder.binding.viewItemHomeNewsTitleText.text = newItem.cityName
        val messageListAdapter = MessageListAdapter(childRecyclerViewId, this)
        holder.binding.homeNewsRecyclerView.adapter = messageListAdapter
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
        notifyItemChanged(childRecyclerViewId)
    }
}