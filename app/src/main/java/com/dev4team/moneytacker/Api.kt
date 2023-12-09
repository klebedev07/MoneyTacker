package com.dev4team.moneytacker

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/items")
    fun getItems(): Call<List<Item>>
}