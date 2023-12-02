package com.sterdevs.mybills.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sterdevs.mybills.R
import com.sterdevs.mybills.databinding.HomeDetailsActivityBinding

class HomeDetailsActivity : AppCompatActivity() {
    private lateinit var binding :HomeDetailsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title").toString()
        val business = intent.getStringExtra("business")
        val location = intent.getStringExtra("location")
        val titlef : TextView = binding.fragmentHomedetailBillTitle
        titlef.text = title


        // Faites ce que vous devez faire avec les données dans l'activité HomeDetails
    }
}