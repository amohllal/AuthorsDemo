package com.assignment.data.mapper

import com.assignment.data.model.local.AuthorsEntity
import com.assignment.data.model.remote.author.AuthorsDataResponse
import com.assignment.data.model.remote.author.AuthorsDataResponseItem
import com.assignment.data.model.remote.posts.PostsDataResponse
import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.model.PostsDomainResponse
import com.assignment.domain.model.PostsDomainResponseItem

fun AuthorsDataResponse.mapToDomain() = AuthorsDomainResponse(this.map {
    AuthorsDomainResponseItem(
        avatarUrl = it.avatarUrl,
        name = it.name,
        id = it.id,
        userName = it.userName,
        email = it.email
    )
})

fun PostsDataResponse.mapToDomain() = PostsDomainResponse(this.map {
    PostsDomainResponseItem(
        date = it.date,
        imageUrl = it.imageUrl,
        title = it.title,
        body = it.body
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
fun List<AuthorsDataResponseItem>.mapToDomainResponse() = AuthorsDomainResponse(this.map {
    AuthorsDomainResponseItem(avatarUrl = it.avatarUrl, name = it.name, id = it.id, userName = it.userName, email = it.email)
})


fun AuthorsDomainResponseItem.mapToEntities() =
    AuthorsEntity(
        avatarUrl = this.avatarUrl,
        name = this.name,
        userName = this.userName,
        id = this.id
    )

fun AuthorsDataResponseItem.mapToEntity() = AuthorsEntity(
    avatarUrl = this.avatarUrl,
    name = this.name,
    userName = this.userName,
    id = this.id
)

fun AuthorsEntity.mapToDomain() =
    AuthorsDomainResponseItem(
        avatarUrl = this.avatarUrl,
        name = this.name,
        id = this.id,
        userName = this.userName,
        email = this.email
    )