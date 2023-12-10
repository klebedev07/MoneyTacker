package com.dev4team.moneytacker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemListViewHolder>() {

    private var items: MutableList<Item> = mutableListOf()

    fun setData(items: MutableList<Item>) {
        this.items = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        return ItemListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        items[position].let { holder.applyData(it) }
    }

    fun addItem(item: Item) {
        items.add(items.size, item)
        notifyItemInserted(items.size)
    }

    class ItemListViewHolder(
        private val itemView: View,
        private val title: TextView = itemView.findViewById(R.id.title),
        private val price: TextView = itemView.findViewById(R.id.price)
    ) : RecyclerView.ViewHolder(itemView) {
        fun applyData(item: Item) {
            title.text = item.title
            price.text = item.price
        }
    }

}