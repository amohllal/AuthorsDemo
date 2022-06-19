package com.assignment.domain.usecase

import com.assignment.domain.repository.AuthorsRepository
import javax.inject.Inject

class GetAuthorsListUseCase @Inject constructor(private val authorRepo: AuthorsRepository) {
    suspend fun getAuthors() = authorRepo.getAuthorsListFromAPI()

   suspend fun getAuthorsFromStorage() = authorRepo.getAuthorsListFromLocalStorage()


}