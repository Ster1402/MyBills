package com.sterdevs.mybills.features.home.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.databinding.FragmentHomeBinding
import com.sterdevs.mybills.features.home.ui.adapters.HomeListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ScreenUtils {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeListAdapter
    private lateinit var floatingButtonAddHome: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        getViews()
        addViewsEventsListeners()
        return binding.root
    }

    private fun showBottomSheet(fragment: BottomSheetDialogFragment) {
        fragment.show(childFragmentManager, fragment.tag)
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeListAdapter()
        recyclerView = binding.homeRecyclerview
        recyclerView.adapter = homeAdapter
    }

    override fun getViews() {
        setupRecyclerView()
        floatingButtonAddHome = binding.fragmentHomeBtnAddHome
    }

    override fun initializeDefaultValues() {
        TODO("Not yet implemented")
    }

    override fun addViewsEventsListeners() {
        floatingButtonAddHome.setOnClickListener { showBottomSheet(AddHomeFragment()) }
    }

}