package com.sterdevs.mybills.features.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.databinding.ViewItemMessageBinding
import com.sterdevs.mybills.features.news.domain.models.NewItem

class NewListViewAdapter
    (private val childRecyclerViewId: Int,
     private val onChildItemClickListener: OnChildItemClickListener)
    : RecyclerView.Adapter<NewListViewAdapter.NewViewHolder>() {
    private val infomessages: MutableList<NewItem> = generateNewItems()

    interface OnChildItemClickListener {
        abstract val childRecyclerViewId: Int

        fun onChildItemClick(childRecyclerViewId: Int)
    }

    inner class NewViewHolder(val binding: ViewItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val childItems = adapterPosition
                if (childItems != RecyclerView.NO_POSITION) {
                    val infosms = infomessages[childItems]
                    onChildItemClickListener.onChildItemClick(childRecyclerViewId)
                   // binding.viewItemHomeNewsClearMessageButton.visibility = View.VISIBLE
                }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val binding =
            ViewItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewViewHolder(binding)

    }

    override fun getItemCount(): Int = infomessages.size

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        val infosms = infomessages[position]
        holder.binding.viewItemMessageSender.text = infosms.role
        holder.binding.viewItemMessageTime.text = infosms.time
        holder.binding.viewItemMessageText.text = infosms.messages

    }

    fun generateNewItems(): MutableList<NewItem> {
        val childItems = mutableListOf<NewItem>()
        childItems.add(
            NewItem(
                1,
                "The owner",
                "Bonjour à tous,\n" +
                        "Les travaux de réhabilitation de l'immeuble commenceront ce Weekend.",
                "45 min ago"
            )
        )
        childItems.add(
            NewItem(
                2,
                "The carataker",
                "Bonjour à tous,\n" +
                        "Vous devez pensez à payer vos loyer avant la fin de ce mois. Pour les nouveaux c'est minimum 6mois.",
                "05 min ago"
            )
        )
        return childItems
    }
}