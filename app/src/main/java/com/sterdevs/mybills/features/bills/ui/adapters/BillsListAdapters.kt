package com.sterdevs.mybills.features.bills.ui.adapters

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sterdevs.mybills.R
import com.sterdevs.mybills.core.domain.models.enums.BillsTags
import com.sterdevs.mybills.core.ui.adapters.CarouselImagesAdapter
import com.sterdevs.mybills.databinding.ViewItemBillsBinding
import com.sterdevs.mybills.features.bills.domain.models.Bill

class BillsListAdapters(private val clickListener: BillItemClickListener) :
    RecyclerView.Adapter<BillsListAdapters.BillViewHolder>() {

    // TODO: Remove this and take the list as parameter
    private val bills: MutableList<Bill> = generateBills()

    interface BillItemClickListener {
        fun onShowMoreClicked(position: Int)
        fun onPayClicked(position: Int)
    }

    inner class BillViewHolder(val binding: ViewItemBillsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewBillItemShowMoreButton.setOnClickListener {
                MaterialAlertDialogBuilder(itemView.context)
                    .setTitle("Show more")
                    .setMessage("This feature is not yet available")
                    .setNeutralButton("Cancel") { _, _ ->
                        // Respond to neutral button press
                    }
                    .setPositiveButton("OK") { _, _ ->
                        // TODO : Respond to positive button press
                    }
                    .show()
            }

            binding.viewItemPayButton.setOnClickListener {
                MaterialAlertDialogBuilder(itemView.context)
                    .setTitle("Show more")
                    .setMessage("This feature is not yet available")
                    .setNeutralButton("Cancel") { _, _ ->
                        // Respond to neutral button press
                    }
                    .setPositiveButton("OK") { _, _ ->
                        // TODO : Respond to positive button press
                    }
                    .show()
            }
        }
    }

    private fun generateBills(): MutableList<Bill> {
        val items = mutableListOf<Bill>()
        val electricityBill = Bill(
            id = 1,
            tag = "Electricity",
            cost = 3000.0,
            forPeriod = "July 2023"
        )

        val waterBill = Bill(
            id = 2,
            tag = "Water",
            cost = 5000.0,
            forPeriod = "July 2023"
        )

        // Ajoutez autant de factures que nécessaire
        items.add(electricityBill)
        items.add(waterBill)

        return items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val binding =
            ViewItemBillsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BillViewHolder(binding)
    }

    override fun getItemCount(): Int = bills.size

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val bill = bills[position]

        holder.binding.apply {
            val pictures = listOf(
                R.drawable.no_content_image,
                R.drawable.no_content_image,
            )

            if (bill.tag == BillsTags.ELECTRICITY.value || bill.tag == BillsTags.WATER.value) {
                val carouselAdapter = CarouselImagesAdapter(pictures)
                viewItemBillRecyclerviewCarousel.adapter = carouselAdapter
            } else {
                viewItemBillRecyclerviewCarousel.visibility = View.GONE
                viewItemBillsCarouselDivider.visibility = View.INVISIBLE
            }

            viewItemBillTextPrice.text = buildString {
                append(bill.cost.toString())
                append(" XFA")
            }

            when (bill.tag) {
                BillsTags.ELECTRICITY.value -> {
                    viewItemBillTxtType.setText(R.string.title_bills_type_electricity)
                    viewItemBillIconType.setImageResource(R.drawable.flash_decor)
                }

                BillsTags.WATER.value -> {
                    viewItemBillTxtType.setText(R.string.title_bills_type_water)
                    viewItemBillIconType.setImageResource(R.drawable.water)
                }

                else -> {
                    viewItemBillTxtType.setText(R.string.title_bills_type_rent)
                    viewItemBillIconType.visibility = View.GONE
                }
            }

            viewItemBillTxtDeliveredDate.text = buildString {
                append(DateFormat.format("d MMM y", bill.updatedAt))
            }
            viewItemBillTxtDeadlineDate.text = DateFormat.format("d MMM y", bill.deadlineAt)
        }
    }
}
