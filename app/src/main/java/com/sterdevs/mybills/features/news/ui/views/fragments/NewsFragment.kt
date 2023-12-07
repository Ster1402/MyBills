package com.sterdevs.mybills.features.news.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sterdevs.mybills.R
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.databinding.FragmentNewsBinding
import com.sterdevs.mybills.features.news.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(), ScreenUtils {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun getViews() {
        TODO("Not yet implemented")
    }

    override fun initializeDefaultValues() {
        TODO("Not yet implemented")
    }

    override fun addViewsEventsListeners() {
        TODO("Not yet implemented")
    }
}