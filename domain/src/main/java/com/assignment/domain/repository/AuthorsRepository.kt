package com.assignment.domain.repository

import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import io.reactivex.Single

interface AuthorsRepository {
    fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse>

    fun getAuthorsListFromLocalStorage(): Single<List<AuthorsDomainResponseItem>>
}