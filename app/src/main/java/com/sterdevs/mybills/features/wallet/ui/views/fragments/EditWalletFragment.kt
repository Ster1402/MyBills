package com.sterdevs.mybills.features.wallet.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sterdevs.mybills.R
import com.sterdevs.mybills.databinding.BottomSheetDialogFragmentEditWalletBinding
import com.sterdevs.mybills.features.wallet.domain.models.PaymentMethod

class EditWalletFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDialogFragmentEditWalletBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDialogFragmentEditWalletBinding.inflate(inflater, container, false)

        return binding.root
    }

}