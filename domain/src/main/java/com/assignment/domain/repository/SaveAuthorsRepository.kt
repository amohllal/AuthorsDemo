package com.assignment.domain.repository

import com.assignment.domain.model.AuthorsDomainResponseItem

interface SaveAuthorsRepository {
    fun saveAuthors(authorList: List<AuthorsDomainResponseItem>)
}