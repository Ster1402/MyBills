package com.sterdevs.mybills.features.home.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.core.domain.models.Home
import com.sterdevs.mybills.databinding.ViewItemHomeBinding
import com.sterdevs.mybills.features.home.ui.views.activities.HomeDetailsActivity

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.HomeViewHolder>() {
    private val homes: MutableList<Home> = generateHomeItems()

    inner class HomeViewHolder(val binding: ViewItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val home = homes[position]
                    launchHomeDetails(home)
                }
            }
        }

        private fun launchHomeDetails(homeItem: Home) {
            val intent = Intent(itemView.context, HomeDetailsActivity::class.java)
            intent.putExtra("title", homeItem.name)
            intent.putExtra("business", homeItem.slug)
            intent.putExtra("location", homeItem.location)
            itemView.context.startActivity(intent)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ViewItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = homes.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val home = homes[position]
        holder.binding.viewItemHomeTitle.text = home.name
        holder.binding.viewItemHomeSlug.text = home.slug
        holder.binding.viewItemHomeLocation.text = home.location
        holder.binding.viewItemHomeCaretaker.text = "Caretaker Name"
        if (home.showBadge) {
            holder.binding.viewItemHomeBadgeCard.isVisible = true
            holder.binding.viewItemHomeUnpaidBillsCount.text = home.unpaidBillsCount.toString()
        } else {
            holder.binding.viewItemHomeBadgeCard.isVisible = false
        }


    }

    fun generateHomeItems(): MutableList<Home> {
        // Données pour le RecyclerView
        val items = mutableListOf<Home>()
        items.add(
            Home(
                1,
                "Big Ben City",
                "@big-ben-city",
                "Douala, Pk19 entrée Madi",
                false,
                0,
                "",
                caretakerId = 1,

            )
        )
        items.add(
            Home(
                2,
                "Chicago City",
                "@chicago-city",
                "Douala, Pk17 entrée gendarmerie",

                true,
                5,
                "Batiment A",
                caretakerId = 1
            )
        )
        return items
    }

}

