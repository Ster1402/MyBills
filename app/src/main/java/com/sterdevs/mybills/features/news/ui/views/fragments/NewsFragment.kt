package com.sterdevs.mybills.features.news.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.databinding.FragmentNewsBinding
import com.sterdevs.mybills.features.news.ui.adapters.NewListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(), ScreenUtils {
    private lateinit var binding:FragmentNewsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var newAdapter: NewListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        getViews()
        addViewsEventsListeners()
        return binding.root
    }

    private fun setupRecyclerView(){
        newAdapter = NewListAdapter(2)
      //  newAdapter.setOnItemClickListener(this)
        recyclerView = binding.homeNewsRecyclerView
        recyclerView.adapter = newAdapter

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