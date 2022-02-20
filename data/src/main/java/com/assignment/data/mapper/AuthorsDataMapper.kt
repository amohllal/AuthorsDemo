package com.assignment.data.mapper

import com.assignment.data.model.local.AuthorsEntity
import com.assignment.data.model.remote.AuthorsDataResponse
import com.assignment.data.model.remote.AuthorsDataResponseItem
import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem

fun AuthorsDataResponse.mapToDomain() = AuthorsDomainResponse(this.map {
    AuthorsDomainResponseItem(
        avatarUrl = it.avatarUrl,
        name = it.name,
        id = it.id,
        userName = it.userName,
        email = it.email
    )
})

fun List<AuthorsEntity>.mapToRemoteResponse() = this.map {
    AuthorsDataResponseItem(
        address = null,
        avatarUrl = it.avatarUrl!!,
        email = it.email!!,
        id = it.id!!,
        name = it.name!!,
        userName = it.userName!!
    )
}