package com.sterdevs.mybills.features.home.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.databinding.FragmentHomeBinding
import com.sterdevs.mybills.features.home.ui.adapters.HomeListAdapter

class HomeFragment: Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val add_home_view = binding.addHome
        add_home_view.setOnClickListener{ showBottomSheet(AddHomeFragment())}
        val view = binding.root
        setupRecyclerView()
        return view
    }
    private fun showBottomSheet(fragment: BottomSheetDialogFragment) {
        fragment.show(childFragmentManager, fragment.tag)
    }
    private fun setupRecyclerView() {
        homeAdapter = HomeListAdapter()
        recyclerView = binding.recyclerViewId
        recyclerView.adapter = homeAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

}