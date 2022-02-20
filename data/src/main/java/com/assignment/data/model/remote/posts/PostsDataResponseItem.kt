package com.assignment.data.model.remote.posts


import com.google.gson.annotations.SerializedName

data class PostsDataResponseItem(
    @SerializedName("authorId")
    val authorId: Int,
    @SerializedName("body")
    val body: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("title")
    val title: String
)