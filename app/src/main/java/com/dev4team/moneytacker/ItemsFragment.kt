package com.dev4team.moneytacker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalStateException

class ItemsFragment(private val layoutId: Int) : Fragment() {
    companion object {
        private const val TYPE_KEY = "type"
        const val TYPE_UNKNOWN = -1
        const val TYPE_INCOMES = 1
        const val TYPE_EXPENSES = 2
        const val TYPE_BALANCE = 3

        fun create(type: Int, layoutId: Int): ItemsFragment {
            val fragment = ItemsFragment(layoutId)
            val bundle = Bundle()
            bundle.putInt(TYPE_KEY, type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        val type = bundle?.getInt(TYPE_KEY, TYPE_UNKNOWN)
        if(type == TYPE_UNKNOWN) {
            throw IllegalStateException()
        }
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
            it.adapter = ItemsAdapter()
        }

    }
}



