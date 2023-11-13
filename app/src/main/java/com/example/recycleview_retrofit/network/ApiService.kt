package com.example.recycleview_retrofit.network

import com.example.recycleview_retrofit.model.Responseee
import retrofit2.Call
import retrofit2.http.GET

// Antar muka (interface) ApiService berfungsi sebagai kontrak untuk endpoint-endpoint yang dapat diakses dengan Retrofit
interface ApiService {
    // Anotasi @GET menandakan bahwa ini adalah permintaan HTTP GET
    // "data.php" adalah bagian dari URL endpoint yang akan diakses
    // Fungsi ini mengembalikan objek Call<Responseee> yang dapat digunakan oleh Retrofit untuk mengelola respons dari permintaan HTTP
    @GET("data.php")
    fun getMovie(): Call<Responseee>
}
