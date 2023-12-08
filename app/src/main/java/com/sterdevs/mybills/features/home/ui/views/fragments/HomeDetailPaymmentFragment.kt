package com.sterdevs.mybills.features.home.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sterdevs.mybills.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailPaymmentFragment :  BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_home_detail_payment, container, false)

    companion object {
        const val TAG = "ModalBottomSheetHomeDetail"
    }
}