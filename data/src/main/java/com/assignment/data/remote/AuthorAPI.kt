package com.assignment.data.remote

import com.assignment.data.model.remote.author.AuthorsDataResponse
import com.assignment.data.model.remote.posts.PostsDataResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorAPI {

    @GET("authors")
    fun getAuthors(): Single<AuthorsDataResponse>

    @GET("posts")
    fun getPosts(@Query("authorId") userId: String): Single<PostsDataResponse>
}