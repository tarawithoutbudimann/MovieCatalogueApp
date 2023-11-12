package com.example.recycleview_retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleview_retrofit.model.ResultItem
import com.example.recycleview_retrofit.databinding.ItemMupiBinding

// typealias untuk menyederhanakan definisi listener klik
typealias onClickmupi = (ResultItem) -> Unit

// Adapter RecyclerView untuk item Mupi
class mupiItemAdapt(
    private val listmupi: ArrayList<ResultItem>, // Daftar item Mupi
    private val onClickmupi: onClickmupi // Listener klik untuk item Mupi
) : RecyclerView.Adapter<mupiItemAdapt.itemMupiViewHolder>() {

    // ViewHolder untuk item Mupi
    inner class itemMupiViewHolder(private val binding: ItemMupiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Memasangkan data ke ViewHolder
        fun bind(data: ResultItem) {
            with(binding) {
                // Set judul
                txtMupi.text = data.title

                // Set listener klik untuk item
                itemView.setOnClickListener {
                    onClickmupi(data) // Memanggil listener klik
                }

                // Memuat dan menampilkan gambar menggunakan perpustakaan Glide
                Glide.with(itemView.context).load(data.image).into(binding.imageMupi)
            }
        }
    }

    // Memperbarui data adapter dengan daftar yang difilter
    fun setFilteredList(filteredData: List<ResultItem>) {
        listmupi.clear() // Menghapus data yang ada
        listmupi.addAll(filteredData) // Menambahkan data yang difilter
        notifyDataSetChanged() // Memberi tahu adapter bahwa data telah berubah
    }

    // Membuat ViewHolder baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemMupiViewHolder {
        val binding =
            ItemMupiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return itemMupiViewHolder(binding)
    }

    // Memasangkan data ke ViewHolder pada posisi tertentu
    override fun onBindViewHolder(holder: itemMupiViewHolder, position: Int) {
        holder.bind(listmupi[position])
    }

    // Mengembalikan jumlah total item dalam kumpulan data
    override fun getItemCount(): Int = listmupi.size
}
