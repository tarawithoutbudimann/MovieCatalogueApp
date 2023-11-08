package com.example.recycleview_retrofit

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview_retrofit.adapter.mupi
import com.example.recycleview_retrofit.adapter.mupiItemAdapt
import com.example.recycleview_retrofit.databinding.ActivityMainBinding
import com.example.recycleview_retrofit.model.ResultItem
import com.example.recycleview_retrofit.model.Responseee
import com.example.recycleview_retrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptermupi: mupiItemAdapt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getMovie()
        val mupidatalist = ArrayList<ResultItem>() // data yang mau dimasukkan

        response.enqueue(object : Callback<Responseee> {
            override fun onResponse(call: Call<Responseee>, response: Response<Responseee>) {
                val thisResponse = response.body()
                val data = thisResponse?.result ?: emptyList()
                // pengecekan member
                if (data.isNotEmpty()){
                    for (item in data){
                        val itemMupiData = ResultItem(
                            item?.image,
                            item?.id,
                            item?.title
                        )
                        if (item != null) {
                            mupidatalist.add(item)
                        }
                    }
                    adaptermupi = mupiItemAdapt(mupidatalist) { mupi ->
                        Toast.makeText(this@MainActivity, "anjay", Toast.LENGTH_SHORT).show()
                    }
                    with(binding){
                        RV.apply {
                            adapter = adaptermupi
                            layoutManager = GridLayoutManager(this@MainActivity, 2)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Responseee>, t: Throwable) {
                Log.d("error", "" + t.stackTraceToString())
            }

        })

    }
}