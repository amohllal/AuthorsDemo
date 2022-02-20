package com.assignment.domain.repository

import com.assignment.domain.model.PostsDomainResponse
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPostsForUser(userId: String): Flow<PostsDomainResponse>

}