package com.example.recycleview_retrofit.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objek ApiClient bertanggung jawab untuk mengelola konfigurasi Retrofit dan menyediakan instance ApiService
object ApiClient {
    // URL dasar dari API
    val baseUrl = "https://demo.lazday.com/rest-api-sample/"

    // Fungsi getInstance() mengembalikan instance ApiService yang telah dikonfigurasi
    fun getInstance(): ApiService {
        // Membuat interceptor untuk logging HTTP requests dan responses
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        // Membuat objek OkHttpClient dengan menambahkan interceptor untuk logging
        val mOkHttpClient= OkHttpClient.Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        // Membuat objek Retrofit dengan konfigurasi dasar
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)  // Menetapkan URL dasar
            .addConverterFactory(GsonConverterFactory.create())  // Menggunakan GsonConverterFactory untuk mengonversi JSON ke objek Kotlin
            .client(mOkHttpClient)  // Menetapkan OkHttpClient yang telah dikonfigurasi
            .build()

        // Menghasilkan instance ApiService dari objek Retrofit
        return builder.create(ApiService::class.java)
    }
}
