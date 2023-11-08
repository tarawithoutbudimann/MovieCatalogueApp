package com.example.recycleview_retrofit.model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null
)

data class ResultItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)
