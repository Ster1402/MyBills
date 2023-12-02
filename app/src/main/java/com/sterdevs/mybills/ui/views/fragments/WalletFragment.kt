package com.sterdevs.mybills.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.R
import com.sterdevs.mybills.databinding.FragmentWalletBinding
import com.sterdevs.mybills.databinding.ItemHomeBinding
import com.sterdevs.mybills.databinding.ItemWalletBinding

class WalletFragment: Fragment() {
    private lateinit var binding: FragmentWalletBinding
    private lateinit var recyclerView: RecyclerView
    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = FragmentWalletBinding.inflate(inflater, container, false)
         recyclerView = binding.recyclerViewId
        setupRecyclerView()

        val view = binding.root

        setupRecyclerView()

        return view
    }
    private class WalletItemViewHolder(val binding: ItemWalletBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class WalletAdapter(private val items: List<WalletItem>) :
        RecyclerView.Adapter<WalletItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletItemViewHolder {
            val binding = ItemWalletBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
            return WalletItemViewHolder(binding)
        }


        // Lier les données avec le ViewHolder
        override fun onBindViewHolder(holder: WalletItemViewHolder, position: Int) {
            val currentItem = items[position]
            // Lier les données avec le ViewHolder
            holder.binding.operatorName.text = currentItem.operatorName
            holder.binding.userName.text = currentItem.userName
            holder.binding.userNumber.text = currentItem.userNumber
            // Ajouter d'autres liaisons nécessaires

            // Gérer les clics ou les interactions ici si nécessaire
            holder.binding.root.setOnClickListener {
                // Action lors du clic sur l'élément du RecyclerView
            }

        }

        override fun getItemCount(): Int = items.size
    }
    private data class WalletItem(val operatorName: String, val userName: String, val userNumber: String)
    private fun setupRecyclerView() {
        val walletItems = generateHomeItems()
        val adapter = WalletAdapter(walletItems)

        // Utilisez requireContext() au lieu de this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
    private fun generateHomeItems(): List<WalletItem> {
        // Données pour le RecyclerView
        val items = mutableListOf<WalletItem>()
        items.add(WalletItem("Orange MOney", " Jordy", "656316666"))
        items.add(WalletItem("Mobile Money", "Sterdevs", " 656656560"))
        items.add(WalletItem("Orange MOney", " Jordy", "656316666"))
        items.add(WalletItem("Mobile Money", "Sterdevs", " 656656560"))
        items.add(WalletItem("Orange MOney", " Jordy", "656316666"))
        items.add(WalletItem("Mobile Money", "Sterdevs", " 656656560"))
        return items
    }
}

