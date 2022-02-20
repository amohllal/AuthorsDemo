package com.assignment.data.remote

import com.assignment.data.model.remote.AuthorsDataResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AuthorAPI {

    @GET("authors")
    fun getAuthors(): Single<AuthorsDataResponse>
}