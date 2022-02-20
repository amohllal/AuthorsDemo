package com.assignment.data.model.remote.author


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)