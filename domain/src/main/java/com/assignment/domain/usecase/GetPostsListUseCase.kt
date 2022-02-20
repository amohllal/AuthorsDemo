package com.assignment.domain.usecase

import com.assignment.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsListUseCase @Inject constructor(private val postsRepo: PostsRepository) {

    suspend fun getPostsForUser(userId: String) = postsRepo.getPostsForUser(userId)
}