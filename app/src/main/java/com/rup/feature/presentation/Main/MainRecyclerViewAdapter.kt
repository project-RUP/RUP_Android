package com.rup.feature.presentation.Main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rup.databinding.ItemMainBinding
import com.rup.feature.presentation.Main.model.maindto
interface OnItemClickListener {
    fun onItemClick(position: maindto)
}
class MainRecyclerViewAdapter(private val listener: OnItemClickListener,private var items: List<maindto>) : RecyclerView.Adapter<MainRecyclerViewAdapter.MainPageViewHolder>() {

    class MainPageViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainPageViewHolder, position: Int) {
        val item = items[position]

        // Binding을 사용하여 데이터를 뷰에 연결
        with(holder.binding) {
            title.text = item.title
            subscription.text = item.subscription
            time.text = item.time
            time2.text = item.amPm
            headcount.text = item.headCount
            location.text = item.location
            price.text = item.price

            root.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    fun updateData(newItems: List<maindto>) {
        this.items = newItems
        notifyDataSetChanged()  // Notify RecyclerView to update
    }

    override fun getItemCount(): Int = items.size
}