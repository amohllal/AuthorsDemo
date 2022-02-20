package com.assignment.domain.usecase

import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.repository.SaveAuthorsRepository
import javax.inject.Inject

class SaveAuthorsUseCase @Inject constructor(private val saveRepo: SaveAuthorsRepository) {
    fun saveAuthors(authorsList: List<AuthorsDomainResponseItem>) {
        saveRepo.saveAuthors(authorsList)
    }
}