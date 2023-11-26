package com.sterdevs.mybills.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sterdevs.mybills.databinding.FragmentAddHomeBinding


class AddHomeFragment :  BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        //Fermer le bottom Sheet
        val buttonClose = binding.buttonClose
        buttonClose.setOnClickListener { closeBottomSheet() }

    return view
    }
    // Fonction pour fermer le bottom sheet
    private fun closeBottomSheet() {
        val bottomSheetDialogFragment = parentFragment as? BottomSheetDialogFragment
        bottomSheetDialogFragment?.dismiss()
    }

}