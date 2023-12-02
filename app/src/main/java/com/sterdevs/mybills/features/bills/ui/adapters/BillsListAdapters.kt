package com.sterdevs.mybills.features.bills.ui.adapters

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDivider
import com.sterdevs.mybills.R
import com.sterdevs.mybills.core.domain.models.enums.BillsTags
import com.sterdevs.mybills.core.ui.adapters.CarouselImagesAdapter
import com.sterdevs.mybills.features.bills.domain.models.Bill

class BillsListAdapters(val bills: List<Bill>) :
    RecyclerView.Adapter<BillsListAdapters.BillViewHolder>() {
    // View Holder for bills
    class BillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var carouselRecyclerView: RecyclerView =
            itemView.findViewById(R.id.view_item_bill_recyclerview_carousel)
        var billTextCost: TextView = itemView.findViewById(R.id.view_item_bill_txt_price)
        var billTextType: TextView = itemView.findViewById(R.id.view_item_bill_txt_type)
        var billImageType: ImageView = itemView.findViewById(R.id.view_item_bill_icon_type)
        var billIssueDate: TextView = itemView.findViewById(R.id.view_item_bill_txt_delivered_date)
        var billDeadline: TextView = itemView.findViewById(R.id.view_item_bill_txt_deadline_date)
        var carouselDivider: MaterialDivider =
            itemView.findViewById(R.id.view_item_bills_carousel_divider)
    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_bills, parent, false)

        return BillViewHolder(itemView)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int = bills.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val bill = bills[position]

        holder.apply {
            // Pictures : TODO: Replace with correct images (bill.pictures relationship)
            val pictures = listOf(
                R.drawable.no_content_image,
                R.drawable.no_content_image,
            )

            if (bill.tag == BillsTags.ELECTRICITY.value || bill.tag == BillsTags.WATER.value) {
                val carouselAdapter = CarouselImagesAdapter(pictures)
                carouselRecyclerView.adapter =
                    carouselAdapter
            } else {
                carouselRecyclerView.visibility = View.GONE
                carouselDivider.visibility = View.INVISIBLE
            }

            billTextCost.text = buildString {
                append(bill.cost.toString())
                append(" XFA")
            }
            when (bill.tag) {
                BillsTags.ELECTRICITY.value -> {
                    billTextType.setText(R.string.title_bills_type_electricity)
                    billImageType.setImageResource(R.drawable.flash_decor)
                }
                BillsTags.WATER.value -> {
                    billTextType.setText(R.string.title_bills_type_water)
                    billImageType.setImageResource(R.drawable.water)
                }
                else -> {
                    billTextType.setText(R.string.title_bills_type_rent)
                    billImageType.visibility = View.GONE
                }
            }
            billIssueDate.text = buildString {
                append(DateFormat.format("d MMM y", bill.updatedAt))
            }
            billDeadline.text = DateFormat.format("d MMM y", bill.deadlineAt)
        }

    }
}