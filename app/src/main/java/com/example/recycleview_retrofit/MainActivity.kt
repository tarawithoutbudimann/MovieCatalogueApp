package com.example.recycleview_retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
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
    private var mupidatalist = ArrayList<ResultItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchView = binding.searchView

        // Inisialisasi objek ApiClient
        val client = ApiClient.getInstance()

        // Mendapatkan data film dari API menggunakan Retrofit
        val response = client.getMovie()

        // Menangani respons dari permintaan Retrofit
        response.enqueue(object : Callback<Responseee> {
            override fun onResponse(call: Call<Responseee>, response: Response<Responseee>) {
                val thisResponse = response.body()
                val data = thisResponse?.result ?: emptyList()

                // Memproses data yang diterima dari API
                if (data.isNotEmpty()) {
                    for (item in data) {
                        val itemMupiData = ResultItem(
                            item?.image,
                            item?.id,
                            item?.title
                        )
                        if (item != null) {
                            mupidatalist.add(itemMupiData)
                        }
                    }

                    // Menginisialisasi dan menetapkan adapter RecyclerView
                    adaptermupi = mupiItemAdapt(mupidatalist) { mupi ->
                        Toast.makeText(this@MainActivity, "film berhasil diklik", Toast.LENGTH_SHORT).show()
                    }
                    with(binding) {
                        RV.apply {
                            adapter = adaptermupi
                            layoutManager = GridLayoutManager(this@MainActivity, 2)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Responseee>, t: Throwable) {
                // Menangani kegagalan permintaan Retrofit
                Log.d("error", "" + t.stackTraceToString())
            }
        })

        // Menetapkan listener untuk SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Memfilter data berdasarkan input pengguna
                filterList(newText)
                return true
            }
        })
    }

    // Fungsi untuk melakukan filter pada data film
    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<ResultItem>()

            // Mengecek setiap item dalam daftar dan menambahkannya ke daftar terfilter jika cocok dengan query
            for (i in mupidatalist) {
                if (i.title?.lowercase(Locale.ROOT)?.contains(query) == true) {
                    filteredList.add(i)
                }
            }

            // Menampilkan pesan jika tidak ada data yang sesuai dengan query
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                // Mengatur daftar terfilter pada adapter RecyclerView
                adaptermupi.setFilteredList(filteredList)
            }
        }
    }
}
