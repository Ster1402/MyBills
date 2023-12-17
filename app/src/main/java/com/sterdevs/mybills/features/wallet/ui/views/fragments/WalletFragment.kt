package com.sterdevs.mybills.features.wallet.ui.views.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sterdevs.mybills.R
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.databinding.FragmentWalletBinding
import com.sterdevs.mybills.features.wallet.ui.adapters.WalletListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : Fragment(), ScreenUtils, WalletListAdapter.WalletItemClickListener {
    private lateinit var binding: FragmentWalletBinding
    private lateinit var buttonAddWallet: MaterialButton
    private lateinit var walletRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletBinding.inflate(inflater, container, false)
        getViews()
        addViewsEventsListeners()
        return binding.root
    }

    override fun onEditClicked(position: Int) {
        showBottomSheet(EditWalletFragment())
    }

    override fun onDeleteClicked(position: Int) {
        context?.let { showDeleteConfirmationDialog(it, position) }
    }

    private fun setupRecyclerView() {
        walletRecyclerView = binding.fragmentWalletRecyclerview
        val walletAdapter = WalletListAdapter(this)
        walletRecyclerView.adapter = walletAdapter
    }

    private fun showBottomSheet(fragment: BottomSheetDialogFragment) {
        fragment.show(childFragmentManager, fragment.tag)
    }

    override fun getViews() {
        setupRecyclerView()
        buttonAddWallet = binding.fragmentWalletBtnAddWallet
    }

    override fun initializeDefaultValues() {
        //
    }

    override fun addViewsEventsListeners() {
        buttonAddWallet.setOnClickListener {
            showBottomSheet(ChooseOperatorFragment())
        }
    }
    private fun showDeleteConfirmationDialog(context: Context, position: Int) {
        MaterialAlertDialogBuilder(context)
            .setTitle(getString(R.string.modal_title))
            .setMessage(getString(R.string.modal_message))
            .setPositiveButton(resources.getString(R.string.ok)) { dialog, _ ->
                Toast.makeText(context, "Élément supprimé!", Toast.LENGTH_SHORT).show()
                // TODO: Ajoutez votre logique de suppression ici

                // Fermez la boîte de dialogue
                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                Toast.makeText(context, "Suppression annulée", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .show()
    }

}

