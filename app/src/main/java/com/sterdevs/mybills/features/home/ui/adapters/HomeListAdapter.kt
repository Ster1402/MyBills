package com.sterdevs.mybills.features.home.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.core.domain.models.Home
import com.sterdevs.mybills.databinding.ItemHomeBinding
import com.sterdevs.mybills.features.home.ui.views.activities.HomeDetailsActivity

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.HomeViewHolder>() {
    private val homes: MutableList<Home> = generateHomeItems()

    inner class HomeViewHolder(val binding: ItemHomeBinding) :
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
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = homes.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val home = homes[position]
        holder.binding.itemTitle.text = home.name
        holder.binding.homeNameRef.text = home.slug
        holder.binding.locationName.text = home.location
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
                "Batiment A",
                caretakerId = 1
            )
        )
        items.add(
            Home(
                2,
                "Chicago City",
                "@chicago-city",
                "Douala, Pk17 entrée gendarmerie",
                "Batiment A",
                caretakerId = 1
            )
        )
        items.add(
            Home(
                3,
                "Big Ben City",
                "@big-ben-city",
                "Douala, Pk19 entrée Madi",
                "Batiment B",
                caretakerId = 1
            )
        )
        items.add(
            Home(
                4,
                "Chicago City",
                "@chicago-city",
                "Douala, Pk17 entrée gendarmerie",
                "Batiment A",
                caretakerId = 1
            )
        )
        items.add(
            Home(
                5,
                "Big Ben City",
                "@big-ben-city",
                "Douala, Pk19 entrée Madi",
                "Batiment A",
                caretakerId = 1
            )
        )
        items.add(
            Home(
                6,
                "Chicago City",
                "@chicago-city",
                "Douala, Pk17 entrée gendarmerie",
                "Batiment A",
                caretakerId = 1
            )
        )
        return items
    }

}

