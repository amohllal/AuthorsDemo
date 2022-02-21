package com.assignment.data.repository

import com.assignment.data.mapper.mapToDomain
import com.assignment.data.remote.AuthorAPI
import com.assignment.domain.model.PostsDomainResponse
import com.assignment.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(private val remoteDataSource: AuthorAPI) :
    PostsRepository {

    override suspend fun getPostsForUser(userId: String): Flow<PostsDomainResponse> {
        return flow {
            val response = remoteDataSource.getPosts(userId)
            if (response.isSuccessful) {
                val body = response.body()
                body?.mapToDomain()?.let { emit(it) }
            }
        }
    }
}