package com.sterdevs.mybills.features.home.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sterdevs.mybills.databinding.ActivityHomeBinding
import com.sterdevs.mybills.databinding.ActivityMainBinding
import com.sterdevs.mybills.databinding.ItemHomeBinding
import com.sterdevs.mybills.features.home.ui.views.fragments.AddHomeFragment
import android.content.Intent

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var navigationBottom: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerViewId
        setupRecyclerView()

        setupDrawerLayout()
        val add_home_view = binding.addHome
        add_home_view.setOnClickListener{ showBottomSheet(AddHomeFragment()) }

    }
    private fun setupDrawerLayout() {
        binding.fragmentHomeLogo.setOnClickListener { openDrawer()}
    }
    private fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
    private fun setupRecyclerView() {
        val HomeItems = generateHomeItems()
        val adapter = HomeAdapter(HomeItems)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
    private fun generateHomeItems(): List<HomeItem> {
        // Données pour le RecyclerView
        val items = mutableListOf<HomeItem>()
        items.add(HomeItem("Big Ben City", " @big-ben-city", "Douala, Pk19 entrée Madi"))
        items.add(HomeItem("Chicago City", "@chicago-city", " Douala, Pk17 entrée gendarmerie"))
        items.add(HomeItem("Big Ben City", " @big-ben-city", "Douala, Pk19 entrée Madi"))
        items.add(HomeItem("Chicago City", "@chicago-city", " Douala, Pk17 entrée gendarmerie"))
        items.add(HomeItem("Big Ben City", " @big-ben-city", "Douala, Pk19 entrée Madi"))
        items.add(HomeItem("Chicago City", "@chicago-city", " Douala, Pk17 entrée gendarmerie"))

        return items
    }

    private inner class HomeAdapter(private val items: List<HomeItem>) :
        RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemBinding =
                ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.binding.itemTitle.text = item.title
            holder.binding.homeNameRef.text = item.business
            holder.binding.locationName.text = item.location
            holder.itemView.setOnClickListener {
                launchHomeDetails(item)
            }
        }
        override fun getItemCount(): Int {
            return items.size
        }
    }

    private data class HomeItem(val title: String, val business: String, val location: String)

    private fun showBottomSheet(fragment: BottomSheetDialogFragment) {
        fragment.show(supportFragmentManager, fragment.tag)
    }
    private fun launchHomeDetails(homeItem: HomeItem) {
        val intent = Intent(this, HomeDetailsActivity::class.java)
        intent.putExtra("title", homeItem.title)
        intent.putExtra("business", homeItem.business)
        intent.putExtra("location", homeItem.location)
        startActivity(intent)
    }


}
