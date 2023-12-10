package com.sterdevs.mybills.features.bills.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.databinding.FragmentHistoryBinding
import com.sterdevs.mybills.features.bills.domain.models.Bill
import com.sterdevs.mybills.features.bills.ui.adapters.BillsListAdapters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(), ScreenUtils {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var billAdapter: BillsListAdapters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        getViews()
        addViewsEventsListeners()
        return binding.root
    }

    private fun setupRecyclerView() {
        val bills = listOf<Bill>()
        billAdapter = BillsListAdapters()
        recyclerView = binding.card
        recyclerView.adapter = billAdapter

    }
    override fun getViews() {
        setupRecyclerView()
        //
    }
    override fun initializeDefaultValues() {
        //
    }
    override fun addViewsEventsListeners() {
        //
    }
}