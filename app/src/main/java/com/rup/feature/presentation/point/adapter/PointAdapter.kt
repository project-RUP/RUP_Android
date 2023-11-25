package com.rup.feature.presentation.point.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rup.databinding.ItemPointHistoryBinding
import com.rup.feature.presentation.point.model.PointHistoryItem

class PointAdapter(private var pointData: List<PointHistoryItem>) :
    RecyclerView.Adapter<PointAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemPointHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setBinding(pointHistoryItem: PointHistoryItem) {
            with(binding) {
                point.text = pointHistoryItem.point
                reservationName.text = pointHistoryItem.reservationName
                date.text = pointHistoryItem.date
                place.text = pointHistoryItem.place
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPointHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = pointData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pointData[position]
        holder.setBinding(item)
    }

    fun setData(pointData: List<PointHistoryItem>) {
        this.pointData = pointData
        notifyDataSetChanged()
    }
}