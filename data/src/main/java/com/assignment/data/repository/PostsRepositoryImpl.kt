package com.assignment.data.repository

import com.assignment.data.mapper.mapToDomain
import com.assignment.data.remote.AuthorAPI
import com.assignment.domain.model.PostsDomainResponse
import com.assignment.domain.repository.PostsRepository
import io.reactivex.Single
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(private val remoteDataSource: AuthorAPI) :
    PostsRepository {
    override fun getPostsForUser(userId: String): Single<PostsDomainResponse> {
        return remoteDataSource.getPosts(userId).map {
            it.mapToDomain()
        }
    }
}