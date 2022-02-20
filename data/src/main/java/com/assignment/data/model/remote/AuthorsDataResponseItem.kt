package com.assignment.data.model.remote


import com.google.gson.annotations.SerializedName

data class AuthorsDataResponseItem(
    @SerializedName("address")
    val address: Address? = null,
    @SerializedName("avatarUrl")
    val avatarUrl: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("userName")
    val userName: String
)