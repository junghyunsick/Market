package com.example.ui.Item

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import com.example.ui.SaleItem
import com.example.ui.databinding.ItemBinding

class ItemAdapter(val mItems: MutableList<SaleItem>) : RecyclerView.Adapter<ItemAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var itemLongClick: ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemLongClick?.onLongClick(it, position)
            true
        }

        holder.binding.iconItem.setImageResource(mItems[position].Image)
        holder.binding.tvItemTitle.text = mItems[position].ItemTitle
        holder.binding.tvAddress.text = mItems[position].Address
        holder.binding.tvPrice.text = mItems[position].Price.toString()
        holder.binding.tvChatCnt.text = mItems[position].ChatCnt.toString()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}