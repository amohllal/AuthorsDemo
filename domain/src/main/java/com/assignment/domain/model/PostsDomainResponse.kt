package com.assignment.domain.model

import com.google.gson.annotations.SerializedName

data class PostsDomainResponse(

	@field:SerializedName("PostsDomainResponse")
	val postsDomainResponse: List<PostsDomainResponseItem>? = null
)

data class PostsDomainResponseItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	)
