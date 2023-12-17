package com.sterdevs.mybills.features.home.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sterdevs.mybills.R
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.databinding.ActivityHomeDetailsBinding
import com.sterdevs.mybills.features.bills.ui.adapters.BillsListAdapters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailsActivity : AppCompatActivity(), ScreenUtils,
    BillsListAdapters.BillItemClickListener {
    private lateinit var binding: ActivityHomeDetailsBinding
    private lateinit var pageTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var billAdapter: BillsListAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gets binding views
        getViews()
        // Initialize defaults values if any
        initializeDefaultValues()
        // Add listeners on views
        addViewsEventsListeners()

        // Go to Home
        binding.backToHome.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun getViews() {
        pageTitle = binding.activityHomeDetailsTitle
        setupRecyclerView()
    }

    override fun initializeDefaultValues() {
        pageTitle.text = intent.getStringExtra("title").toString()
    }

    override fun addViewsEventsListeners() {
        // TODO("Not yet implemented")
    }

    private fun setupRecyclerView() {
        billAdapter = BillsListAdapters(this)
        recyclerView = binding.card
        recyclerView.adapter = billAdapter

    }

    override fun onShowMoreClicked(position: Int) {

    }

    override fun onPayClicked(position: Int) {

    }
}