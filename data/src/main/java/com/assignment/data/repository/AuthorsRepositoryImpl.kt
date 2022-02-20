package com.assignment.data.repository

import com.assignment.data.local.AuthorDAO
import com.assignment.data.mapper.mapToDomain
import com.assignment.data.mapper.mapToRemoteResponse
import com.assignment.data.model.remote.AuthorsDataResponse
import com.assignment.data.remote.AuthorAPI
import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.repository.AuthorsRepository
import io.reactivex.Single
import javax.inject.Inject

class AuthorsRepositoryImpl @Inject constructor
    (
    private val remoteDataSource: AuthorAPI,
    private val localDataSource: AuthorDAO
) : AuthorsRepository {
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

}