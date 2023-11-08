package com.example.recycleview_retrofit.network

import com.example.recycleview_retrofit.model.Responseee
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("data.php")
    fun getMovie(): Call<Responseee>
}