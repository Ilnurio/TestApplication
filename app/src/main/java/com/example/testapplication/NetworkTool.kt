package com.example.testapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkTool {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pizza-and-desserts.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var menuService = retrofit.create(FoodMenuService::class.java)

}