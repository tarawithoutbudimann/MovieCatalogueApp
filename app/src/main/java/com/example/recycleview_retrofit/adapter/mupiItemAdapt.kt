package com.example.recycleview_retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleview_retrofit.model.ResultItem
import com.example.recycleview_retrofit.databinding.ItemMupiBinding

typealias onClickmupi = (ResultItem) -> Unit

class mupiItemAdapt(
    private val listmupi: ArrayList<ResultItem>,
    private val onClickmupi: onClickmupi
) : RecyclerView.Adapter<mupiItemAdapt.itemMupiViewHolder>() {

    inner class itemMupiViewHolder(private val binding: ItemMupiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResultItem) {
            with(binding) {
                txtMupi.text = data.title
                itemView.setOnClickListener {
                    onClickmupi(data)
                }
                Glide.with(itemView.context).load(data.image).into(binding.imageMupi)
            }
        }
    }

    fun setFilteredList(filteredData: List<ResultItem>) {
        listmupi.clear()
        listmupi.addAll(filteredData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemMupiViewHolder {
        val binding =
            ItemMupiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return itemMupiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: itemMupiViewHolder, position: Int) {
        holder.bind(listmupi[position])
    }

    override fun getItemCount(): Int = listmupi.size
}
