package com.sterdevs.mybills.features.home.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sterdevs.mybills.databinding.BottomSheetDialogFragmentAddHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddHomeFragment :  BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetDialogFragmentAddHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDialogFragmentAddHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

}