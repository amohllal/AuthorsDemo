package com.assignment.domain.usecase

import com.assignment.domain.repository.AuthorsRepository
import javax.inject.Inject

class GetAuthorsListUseCase @Inject constructor(private val authorRepo: AuthorsRepository) {
    fun getAuthors() = authorRepo.getAuthorsListFromAPI()
}