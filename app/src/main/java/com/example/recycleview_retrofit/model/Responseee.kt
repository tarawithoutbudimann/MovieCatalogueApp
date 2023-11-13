package com.example.recycleview_retrofit.model

import com.google.gson.annotations.SerializedName

// Kelas data Responseee merepresentasikan format respons dari API, yang memiliki properti "result" berupa List<ResultItem>
data class Responseee (
	@field:SerializedName("result")
	val result: List<ResultItem?>? = null
)

// Kelas data ResultItem merepresentasikan item data film, dengan properti "image" (gambar), "id", dan "title" (judul)
data class ResultItem(
	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)
