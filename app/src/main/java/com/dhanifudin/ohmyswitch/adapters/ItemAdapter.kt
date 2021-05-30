package com.dhanifudin.ohmyswitch.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhanifudin.ohmyswitch.R
import com.dhanifudin.ohmyswitch.models.Item
import com.dhanifudin.ohmyswitch.models.Switch

class ItemAdapter(private val items: ArrayList<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_row_category -> ItemViewHolder(view)
            else -> SwitchViewHolder(view)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val item = items[position]
        when (holder.itemViewType) {
            R.layout.item_row_category -> {
                val itemHolder = holder as ItemViewHolder
                itemHolder.titleText.text = item.title
            }
            else -> {
                val switchHolder = holder as SwitchViewHolder
                val switchItem = item as Switch
                Glide.with(switchHolder.itemView.context)
                    .load(switchItem.thumbnailImage)
                    .into(switchHolder.photoImage)
                switchHolder.titleText.text = switchItem.title
                switchHolder.subtitleText.text = switchItem.subtitle
                switchHolder.characteristicText.text = "${switchItem.characteristic} switching characteristics"
                switchHolder.actuationText.text = "${switchItem.actuationForce} cN operating force"
                switchHolder.switchingPointText.text = "${switchItem.switchingPoint} mm pre travel"
                switchHolder.totalTravelText.text = "${switchItem.totalTravel} mm total travel"
                switchHolder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(item) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = items[position]) {
            is Switch -> {
                return if (item.location == Switch.LEFT)
                    R.layout.item_row_left
                else
                    R.layout.item_row_right
            } else -> {
                R.layout.item_row_category
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SwitchViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var photoImage: ImageView = itemView.findViewById(R.id.avatar_image)
        var titleText: TextView = itemView.findViewById(R.id.title_text)
        var subtitleText: TextView = itemView.findViewById(R.id.subtitle_text)
        var characteristicText: TextView = itemView.findViewById(R.id.characteristic_text)
        var actuationText: TextView = itemView.findViewById(R.id.actuation_text)
        var switchingPointText: TextView = itemView.findViewById(R.id.switching_point_text)
        var totalTravelText: TextView = itemView.findViewById(R.id.total_travel_text)
    }

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var titleText: TextView = itemView.findViewById(R.id.title_text)
    }

    interface OnItemClickCallback {
        fun onItemClicked(item: Switch)
    }
}