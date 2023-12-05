package com.dev4team.moneytacker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemListActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager =
            LinearLayoutManager(this) // подтягивает контекст для наших вложенных сущностей. Типа темы,размеров, цвета и т.д

        val itemPriceTemplate = getString(R.string.item_price_template)
        mockData.forEach {it.price = String.format(itemPriceTemplate, it.price) }

        recyclerView.adapter = ItemListAdapter(mockData)
    }

    private class ItemListAdapter(
        private val items: List<ListItem>) :
        RecyclerView.Adapter<ItemListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
            return ItemListViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
            holder.applyData(items[position])
        }
    }

    private class ItemListViewHolder(
        private val itemView: View,
        private val title: TextView = itemView.findViewById(R.id.title),
        private val price: TextView = itemView.findViewById(R.id.price)
    ) : RecyclerView.ViewHolder(itemView) {

        fun applyData(listItem: ListItem) {
            title.text = listItem.title
            price.text = listItem.price


        }
    }

    companion object {

        private val mockData = listOf(
            ListItem(title = "Moloko", price = 43.toString(), comment = "Купил в перекрестке в понедельник"),
            ListItem(title = "Тунец", price = 203.toString()),
            ListItem(title = "Хлеб", price = 63.toString()),
            ListItem(
                title = "КУрсы по андроид",
                price = 250000.toString(),
                comment = "КУпил что бы стать крутым программистом"
            ),
            ListItem(title = "", price = 1233.toString()),
            ListItem(
                title = "Квадракоптер Мавик",
                price = 1010000000.toString(),
                comment = "Приобрел что бы учиться fpv"
            ),
            ListItem(title = "Макбук", price = 200000.toString(), comment = "Работать"),
            ListItem(title = "Стол для работы", price = 123123.toString()),
            ListItem(title = "Стул для работы", price = 43424.toString()),
            ListItem(title = "НЕ понятно что", price = 3242342.toString()),
            ListItem(title = "Билет в кино", price = 123.toString()),
            ListItem(title = "ЗИмняя куртка", price = 23333.toString()),
            ListItem(title = "Айфон", price = 200000.toString(), comment = "Работать"),
            ListItem(title = "Хз на что", price = 123123.toString()),
            ListItem(title = "Кайфы", price = 43424.toString()),
            ListItem(title = "Новая Машина", price = 3242342.toString()),
            ListItem(title = "Билет в кино", price = 123.toString()),
            ListItem(title = "Джинсы", price = 23333.toString())
        )
    }
}



