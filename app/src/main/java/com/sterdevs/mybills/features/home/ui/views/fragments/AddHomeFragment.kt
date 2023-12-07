package com.sterdevs.mybills.features.home.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sterdevs.mybills.databinding.FragmentAddHomeBinding
import com.sterdevs.mybills.databinding.FragmentEditWalletBinding


class AddHomeFragment :  BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}