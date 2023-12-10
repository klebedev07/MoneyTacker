package com.dev4team.moneytacker

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dev4team.moneytacker.Item.Companion.TYPE_BALANCE
import com.dev4team.moneytacker.Item.Companion.TYPE_EXPENSES
import com.dev4team.moneytacker.Item.Companion.TYPE_INCOMES
import com.dev4team.moneytacker.Item.Companion.TYPE_UNKNOWN
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PAGE_INCOMES = 0
        private const val PAGE_EXPENSE = 1
        private const val PAGE_BALANCE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)


        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.main_tabl_layout)

        val adapter = MainPageAdapter(supportFragmentManager, this)
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
    }

    class MainPageAdapter(fm: FragmentManager, private val context: Context) :
        FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                PAGE_INCOMES -> ItemsFragment.create(TYPE_INCOMES, R.layout.fragment_items)
                PAGE_EXPENSE -> ItemsFragment.create(TYPE_EXPENSES, R.layout.fragment_items)
                PAGE_BALANCE -> ItemsFragment.create(TYPE_BALANCE, R.layout.fragment_balance)
                else -> ItemsFragment.create(TYPE_UNKNOWN, 0)
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                PAGE_INCOMES -> context.getString(R.string.tab_incomes)
                PAGE_EXPENSE -> context.getString(R.string.tab_expenses)
                PAGE_BALANCE -> context.getString(R.string.tab_balance)
                else -> null
            }
        }
    }
}