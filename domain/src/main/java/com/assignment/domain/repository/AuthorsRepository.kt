package com.assignment.domain.repository

import com.assignment.domain.model.AuthorsDomainResponse
import io.reactivex.Single

interface AuthorsRepository {
    fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse>

}