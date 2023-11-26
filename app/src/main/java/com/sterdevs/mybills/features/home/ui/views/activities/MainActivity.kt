package com.sterdevs.mybills.features.home.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy
import com.sterdevs.mybills.commons.core.models.enums.BillsStatus
import com.sterdevs.mybills.commons.core.models.enums.BillsTags
import com.sterdevs.mybills.databinding.ActivityMainBinding
import com.sterdevs.mybills.features.bills.core.models.Bill
import com.sterdevs.mybills.features.bills.ui.adapters.BillsListAdapters
import java.time.Instant
import java.time.Month

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val billsRecyclerView = binding.fragmentBillsRecyclerView
        val billsList = listOf(
            Bill(
                tag = BillsTags.ELECTRICITY.value,
                cost = 5000.0,
                forPeriod = Month.NOVEMBER.name
            ),
            Bill(
                tag = BillsTags.WATER.value,
                unpaidAmount = 2000.0,
                cost = 15000.0,
                forPeriod = Month.DECEMBER.name
            ),
            Bill(
                tag = BillsTags.RENT.value,
                cost = 25000.0,
                forPeriod = Month.DECEMBER.name
            ),
        )

        val adapter = BillsListAdapters(billsList)
        billsRecyclerView.adapter = adapter
    }

}