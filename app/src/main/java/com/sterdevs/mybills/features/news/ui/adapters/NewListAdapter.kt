package com.sterdevs.mybills.features.news.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.databinding.ViewItemMessageBinding
import com.sterdevs.mybills.features.news.domain.models.New

class NewListAdapter : RecyclerView.Adapter<NewListAdapter.NewViewHolder>() {
    private val infomessages: MutableList<New> = generateNewItems()
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(new: New)
    }

    inner class NewViewHolder(val binding: ViewItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val infosms = infomessages[position]
                }
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
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

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(infosms)
        }
    }

    fun generateNewItems(): MutableList<New> {
        val items = mutableListOf<New>()
        items.add(
            New(
                "The owner",
                "Bonjour à tous,\n" +
                        "Les travaux de réhabilitation de l'immeuble commenceront ce Weekend.",
                "45 min ago"
            )
        )
        items.add(
            New(
                "The carataker",
                "Bonjour à tous,\n" +
                        "Vous devez pensez à payer vos loyer avant la fin de ce mois. Pour les nouveaux c'est minimum 6mois.",
                "05 min ago"
            )
        )
        return items
    }

}