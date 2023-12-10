package com.dev4team.moneytacker

data class Item (
    val id: String? = null,
    val title: String,
    val price: String,
    val type: String
) {

    companion object {
        const val TYPE_UNKNOWN = "UNKNOWN"
        const val TYPE_INCOMES = "INCOMES"
        const val TYPE_EXPENSES = "EXPENSE"
        const val TYPE_BALANCE = "BALANCE"
    }
}
