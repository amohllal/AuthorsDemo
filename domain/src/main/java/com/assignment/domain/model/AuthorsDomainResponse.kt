package com.assignment.domain.model

import com.google.gson.annotations.SerializedName

data class AuthorsDomainResponse(

    @field:SerializedName("AuthorsDomainResponse")
    val authorsDomainResponse: List<AuthorsDomainResponseItem>? = null
)

data class AuthorsDomainResponseItem(

    @field:SerializedName("avatarUrl")
    val avatarUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("userName")
    val userName: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)
