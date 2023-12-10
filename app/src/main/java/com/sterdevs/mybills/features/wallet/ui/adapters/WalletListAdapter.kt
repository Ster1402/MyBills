package com.sterdevs.mybills.features.wallet.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
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
                    clickListener.onEditClicked(position)
                }
            }

            binding.deleteOperatorButtonIcon.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
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
    private fun showDeleteConfirmationDialog(context: Context, position: Int) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        alertDialogBuilder.setTitle("Confirmation")
        alertDialogBuilder.setMessage("Voulez-vous vraiment supprimer ce Payment?")

        // Bouton de confirmation
        alertDialogBuilder.setPositiveButton("Oui") { dialog, which ->
            // Supprimez l'élément de votre liste ou effectuez toute autre action
            // en fonction du clic sur le bouton "Oui"
            Toast.makeText(context, "Élément supprimé!", Toast.LENGTH_SHORT).show()
            // TODO: Ajoutez votre logique de suppression ici

            // Fermez la boîte de dialogue
            dialog.dismiss()
        }

        // Bouton d'annulation
        alertDialogBuilder.setNegativeButton("Non") { dialog, which ->
            // Annulez l'action de suppression
            Toast.makeText(context, "Suppression annulée", Toast.LENGTH_SHORT).show()

            // Fermez la boîte de dialogue
            dialog.dismiss()
        }


        alertDialogBuilder.create().show()
    }

}
