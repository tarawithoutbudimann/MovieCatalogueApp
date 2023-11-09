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
import androidx.appcompat.widget.SearchView
import com.example.recycleview_retrofit.adapter.mupiItemAdapt
import com.example.recycleview_retrofit.databinding.ActivityMainBinding
import com.example.recycleview_retrofit.model.ResultItem
import com.example.recycleview_retrofit.model.Responseee
import com.example.recycleview_retrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptermupi: mupiItemAdapt
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchView = findViewById(R.id.searchView)

        val client = ApiClient.getInstance()
        val response = client.getMovie()
        var mupidatalist = ArrayList<ResultItem>()


        response.enqueue(object : Callback<Responseee> {
            override fun onResponse(call: Call<Responseee>, response: Response<Responseee>) {
                val thisResponse = response.body()
                val data = thisResponse?.result ?: emptyList()
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
        // 11. Menetapkan listener untuk perubahan teks pada searchView.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }
    private fun filterList(query: String?) {

            if (query != null) {
                val filteredList = java.util.ArrayList<Responseee>()
                for (i in mupidatalist) {
                    if (i.title.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(i)
                    }
                }

                if (filteredList.isEmpty()) {
                    Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
                } else {
                    adaptermupi.setFilteredList(filteredList)
                }
            }
        }
    }
}