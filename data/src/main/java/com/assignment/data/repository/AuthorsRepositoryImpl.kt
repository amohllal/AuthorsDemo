package com.assignment.data.repository

import com.assignment.data.local.AuthorDAO
import com.assignment.data.mapper.mapToDomain
import com.assignment.data.mapper.mapToEntities
import com.assignment.data.mapper.mapToRemoteResponse
import com.assignment.data.model.remote.AuthorsDataResponse
import com.assignment.data.remote.AuthorAPI
import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.repository.AuthorsRepository
import com.assignment.domain.repository.SaveAuthorsRepository
import io.reactivex.Single
import javax.inject.Inject

class AuthorsRepositoryImpl @Inject constructor
    (
    private val remoteDataSource: AuthorAPI,
    private val localDataSource: AuthorDAO
) : AuthorsRepository, SaveAuthorsRepository {
    override fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse> {
        return remoteDataSource
            .getAuthors()
            .onErrorResumeNext {
                localDataSource.getAuthorsFromDatabase().map {
                    it.mapToRemoteResponse() as AuthorsDataResponse?
                }
            }
            .map {
                it.mapToDomain()
            }
    }

    override fun saveAuthors(authorList: List<AuthorsDomainResponseItem>) {
        localDataSource.saveListOfAuthors(authorList.map {
            it.mapToEntities()
        })
    }
}