package com.assignment.domain.repository

import com.assignment.domain.model.PostsDomainResponse
import io.reactivex.Single

interface PostsRepository {
    fun getPostsForUser(userId: String): Single<PostsDomainResponse>
}