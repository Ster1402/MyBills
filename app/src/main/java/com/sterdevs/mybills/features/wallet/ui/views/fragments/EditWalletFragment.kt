
package com.sterdevs.mybills.features.wallet.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sterdevs.mybills.R
import com.sterdevs.mybills.features.wallet.domain.models.PaymentMethod

class EditWalletFragment :  BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_edit_wallet, container, false)

    companion object {
        fun newInstance(wallet: PaymentMethod): BottomSheetDialogFragment {
            TODO("Not yet implemented")
        }
    }

}