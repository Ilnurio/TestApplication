package com.example.testapplication

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Header


interface FoodMenuService {
    @GET("pizzas")
    suspend fun getPizzas(@Header("X-RapidAPI-Key") xRapidApiKey: String =
                          "9ef64d2ff7msh9d9a445f4a87394p113232jsn23da57829597",
                          @Header("X-RapidAPI-Host") xRapidApiHost: String =
                              "pizza-and-desserts.p.rapidapi.com"): List<PizzaServerItem>
}


data class PizzaServerItem (
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("img") var img: String? = null,
)

