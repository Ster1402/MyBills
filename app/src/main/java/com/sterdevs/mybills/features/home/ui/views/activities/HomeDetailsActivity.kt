package com.sterdevs.mybills.features.home.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sterdevs.mybills.databinding.HomeDetailsActivityBinding
import com.sterdevs.mybills.R
import com.sterdevs.mybills.features.home.ui.views.fragments.HomeFragment
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.features.home.ui.adapters.HomeDetailsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailsActivity : AppCompatActivity(), ScreenUtils {
    private lateinit var binding: HomeDetailsActivityBinding
    private lateinit var pageTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var billAdapter: HomeDetailsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gets binding views
        getViews()
        // Initialize defaults values if any
        initializeDefaultValues()
        // Add listeners on views
        addViewsEventsListeners()

        val title = intent.getStringExtra("title").toString()
        val business = intent.getStringExtra("business")
        val location = intent.getStringExtra("location")

        val titleTextView: TextView = binding.cityName
        titleTextView.text = title

        // Go to Home
        binding.backToHome.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun getViews() {
        setupRecyclerView()
        //replaceToolbarTitle()
        // TODO("Not yet implemented")
    }

    override fun initializeDefaultValues() {
        // TODO("Not yet implemented")
    }

    override fun addViewsEventsListeners() {
        // TODO("Not yet implemented")
    }


    private fun replaceToolbarTitle(fragment: Fragment) {
        when (fragment) {
            is HomeFragment -> {
                pageTitle.text = getString(R.string.title_text_bills)
            }
            else -> pageTitle.text = getString(R.string.dummy_content)
        }
    }
    private fun setupRecyclerView() {
        billAdapter = HomeDetailsListAdapter()
        recyclerView = binding.card
        recyclerView.adapter = billAdapter

    }

}