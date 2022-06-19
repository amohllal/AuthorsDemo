package com.assignment.domain.repository

import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import io.reactivex.Single

interface AuthorsRepository {
    suspend fun getAuthorsListFromAPI(): AuthorsDomainResponse

    suspend fun getAuthorsListFromLocalStorage(): List<AuthorsDomainResponseItem>
}