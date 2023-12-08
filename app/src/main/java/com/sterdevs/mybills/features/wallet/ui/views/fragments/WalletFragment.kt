package com.sterdevs.mybills.features.wallet.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sterdevs.mybills.databinding.FragmentWalletBinding
import com.sterdevs.mybills.features.wallet.ui.adapters.WalletListAdapter

class WalletFragment: Fragment() , WalletListAdapter.WalletItemClickListener {
    private lateinit var binding: FragmentWalletBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var walletAdpater: WalletListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = FragmentWalletBinding.inflate(inflater, container, false)
         val view = binding.root
         setupRecyclerView()
        val paymentMode = binding.buttomAddNewPayment
        paymentMode.setOnClickListener{showBottomSheet(ChooseOperatorFragment())}
        return view
    }
    override fun onEditClicked(position: Int) {
        showBottomSheet(EditWalletFragment())
    }

    override fun onDeleteClicked(position: Int) {
        // Gérer l'événement de suppression ici
    }
    private fun setupRecyclerView() {
        recyclerView = binding.recyclerViewId
        walletAdpater = WalletListAdapter(this)
        recyclerView.adapter = walletAdpater
    }
    private fun showBottomSheet(fragment: BottomSheetDialogFragment) {
        fragment.show(childFragmentManager, fragment.tag)
    }
}

