package com.dev4team.moneytacker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev4team.moneytacker.Item.Companion.TYPE_UNKNOWN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemsFragment(private val layoutId: Int) : Fragment() {

    private lateinit var api: Api
    private lateinit var adapter: ItemsAdapter
    private lateinit var type: String

    companion object {
        private const val TYPE_KEY = "type"

        fun create(type: String, layoutId: Int): ItemsFragment {
            val fragment = ItemsFragment(layoutId)
            val bundle = Bundle()
            bundle.putString(TYPE_KEY, type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemsAdapter()
        val bundle = arguments
        type = bundle?.getString(TYPE_KEY, TYPE_UNKNOWN)!!
        if(type == TYPE_UNKNOWN) {
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
            loadItems(type)
        }

    }

    private fun loadItems(type: String) {
        val call: Call<List<Item>> = api.getItems()

        call.enqueue(object : Callback<List<Item>> {

            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                val items: List<Item>? = response.body()?.filter { it.type == type }
                adapter.setData(items)
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {

            }
        })
    }
}



