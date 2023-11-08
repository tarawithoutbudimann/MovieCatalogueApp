package com.example.recycleview_retrofit.network
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val baseUrl = "https://demo.lazday.com/rest-api-sample/"

    fun getInstance(): ApiService {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        // kalau mau set API kayak header dan lain-lain, itu setnya di mokhttpclient juga (misal kayak buat token)
        val mOkHttpClient= OkHttpClient.Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        // mengubah request dari si get api menjadi bentuk class data model tadi
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()

        return builder.create(ApiService::class.java)
    }
}