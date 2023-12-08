package com.sterdevs.mybills.features.wallet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sterdevs.mybills.databinding.ItemWalletBinding
import com.sterdevs.mybills.features.wallet.domain.models.PaymentMethod

class WalletListAdapter(private val clickListener: WalletItemClickListener)
    : RecyclerView.Adapter<WalletListAdapter.PaymentMethodViewHolder>() {

    private val paymentMethods: MutableList<PaymentMethod> = generatePaymentMethods()

    interface WalletItemClickListener {
        fun onEditClicked(position: Int)
        fun onDeleteClicked(position: Int)
    }

    inner class PaymentMethodViewHolder(val binding: ItemWalletBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.editOperatorButtonIcon.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Utilisez l'interface pour gérer les clics
                    clickListener.onEditClicked(position)
                }
            }

            binding.deleteOperatorButtonIcon.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Utilisez l'interface pour gérer les clics
                    clickListener.onDeleteClicked(position)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val binding = ItemWalletBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentMethodViewHolder(binding)
    }

    override fun getItemCount(): Int = paymentMethods.size

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        val paymentMethod = paymentMethods[position]
        holder.binding.operatorName.text = paymentMethod.operatorName
        holder.binding.itemWalletUsername.text = paymentMethod.userName
        holder.binding.itemWalletUserPhonenumber.text = paymentMethod.userNumber
    }

    private fun generatePaymentMethods(): MutableList<PaymentMethod> {
        // Les données pour le RecyclerView du Wallet
        val items = mutableListOf<PaymentMethod>()
        items.add(PaymentMethod("Orange Money", "Jane Smith", "69456789"))
        items.add(PaymentMethod("MTN MOMO", "Jordy Esprit", "675456789"))
        items.add(PaymentMethod("Orange Money", "SterDevs", "693456789"))
        items.add(PaymentMethod("MTN MOMO", "Jordy Esprit", "673456789"))
        return items
    }
}
