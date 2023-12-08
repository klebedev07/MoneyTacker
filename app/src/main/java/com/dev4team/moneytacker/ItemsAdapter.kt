package com.dev4team.moneytacker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(private val items: List<Item> = mockData) :
    RecyclerView.Adapter<ItemsAdapter.ItemListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        return ItemListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.applyData(items[position])
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

    companion object {
        private val mockData = listOf(
            Item(
                title = "Moloko",
                price = 43.toString(),
                comment = "Купил в перекрестке в понедельник"
            ),
            Item(title = "Тунец", price = 203.toString()),
            Item(title = "Хлеб", price = 63.toString()),
            Item(
                title = "КУрсы по андроид",
                price = 250000.toString(),
                comment = "КУпил что бы стать крутым программистом"
            ),
            Item(title = "", price = 1233.toString()),
            Item(
                title = "Квадракоптер Мавик",
                price = 1010000000.toString(),
                comment = "Приобрел что бы учиться fpv"
            ),
            Item(title = "Макбук", price = 200000.toString(), comment = "Работать"),
            Item(title = "Стол для работы", price = 123123.toString()),
            Item(title = "Стул для работы", price = 43424.toString()),
            Item(title = "НЕ понятно что", price = 3242342.toString()),
            Item(title = "Билет в кино", price = 123.toString()),
            Item(title = "ЗИмняя куртка", price = 23333.toString()),
            Item(title = "Айфон", price = 200000.toString(), comment = "Работать"),
            Item(title = "Хз на что", price = 123123.toString()),
            Item(title = "Кайфы", price = 43424.toString()),
            Item(title = "Новая Машина", price = 3242342.toString()),
            Item(title = "Билет в кино", price = 123.toString()),
            Item(title = "Джинсы", price = 23333.toString())
        )
    }
}