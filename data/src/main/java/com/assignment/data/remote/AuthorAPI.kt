package com.assignment.data.remote

import com.assignment.data.model.remote.author.AuthorsDataResponse
import com.assignment.data.model.remote.posts.PostsDataResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorAPI {

    @GET("authors")
    suspend fun getAuthors(): Response<AuthorsDataResponse>

    @GET("posts")
    suspend fun getPosts(@Query("authorId") userId: String): Response<PostsDataResponse>
}