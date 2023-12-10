package com.dev4team.moneytacker

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dev4team.moneytacker.Item.Companion.TYPE_UNKNOWN
import com.google.android.material.circularreveal.CircularRevealFrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemsFragment(private val layoutId: Int) : Fragment() {

    private lateinit var api: Api
    private lateinit var adapter: ItemsAdapter
    private lateinit var type: String
    private lateinit var refresh: SwipeRefreshLayout

    companion object {
        private const val TYPE_KEY = "type"
        private const val ADD_ITEM_REQUEST_CODE = 123

        fun create(type: String, layoutId: Int): ItemsFragment {
            val fragment = ItemsFragment(layoutId)
            val bundle = Bundle()
            bundle.putString(TYPE_KEY, type)
            bundle.putBoolean("key", true)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemsAdapter()
        val bundle = arguments
        type = bundle?.getString(TYPE_KEY, TYPE_UNKNOWN)!!
        if (type == TYPE_UNKNOWN) {
            throw IllegalStateException()
        }

        val app = activity?.application as App
        api = app.getApi()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.list)?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter

            val fab = view.findViewById<FloatingActionButton>(R.id.fab)
            fab.setOnClickListener {
                val intent = Intent(context, AddItemActivity::class.java)
                intent.putExtra(AddItemActivity.TYPE_KEY, type)
                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE)
            }

            refresh = view.findViewById(R.id.refresh)
            refresh.setColorSchemeColors(Color.BLUE, Color.CYAN, Color.GREEN)
            refresh.setOnRefreshListener {
                loadItems(type)
            }
            loadItems(type)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val name = data?.getStringExtra("name")!!
            val price = data.getStringExtra("price")!!
            adapter.addItem(Item(title = name, price = price, type = type));
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadItems(type: String) {
        val call: Call<List<Item>> = api.getItems()

        call.enqueue(object : Callback<List<Item>> {

            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                response.body()
                    ?.filter { it.type == type }
                    ?.let {
                        adapter.setData(it.toMutableList())

                    }
                refresh.isRefreshing = false

            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                refresh.isRefreshing = false
            }
        })
    }
}



