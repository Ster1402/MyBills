package com.sterdevs.mybills.features.home.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.databinding.ActivityHomeDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailsActivity : AppCompatActivity(), ScreenUtils {
    private lateinit var binding: ActivityHomeDetailsBinding

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

        val title = intent.getStringExtra("title").toString()
        val business = intent.getStringExtra("business")
        val location = intent.getStringExtra("location")

        val titleTextView: TextView = binding.fragmentHomedetailBillTitle
        titleTextView.text = title

        // Go to Home
        /*  binding.backToHome.setOnClickListener {
              it.findNavController().navigate(R.id.action_homeDetailsActivity_to_homeFragment)
          }*/

    }

    override fun getViews() {
        // TODO("Not yet implemented")
    }

    override fun initializeDefaultValues() {
        // TODO("Not yet implemented")
    }

    override fun addViewsEventsListeners() {
        // TODO("Not yet implemented")
    }
}