package com.assignment.data.model.remote.author


import com.google.gson.annotations.SerializedName

data class AuthorsDataResponseItem(
    @SerializedName("address")
    val address: Address? = null,
    @SerializedName("avatarUrl")
    val avatarUrl: String? =null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("userName")
    val userName: String? = null
)