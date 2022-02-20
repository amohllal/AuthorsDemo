package com.assignment.data.repository

import com.assignment.data.local.AuthorDAO
import com.assignment.data.mapper.mapToDomain
import com.assignment.data.mapper.mapToEntity
import com.assignment.data.mapper.mapToRemoteResponse
import com.assignment.data.model.remote.AuthorsDataResponse
import com.assignment.data.remote.AuthorAPI
import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.repository.AuthorsRepository
import io.reactivex.Completable
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
            .flatMap { authorDataResponse ->
                Completable.fromAction {
                    localDataSource.saveListOfAuthors(authorDataResponse.map {
                        it.mapToEntity()
                    })
                }.andThen(Single.just(authorDataResponse))
            }
            .onErrorResumeNext {
                localDataSource.getAuthorsFromDatabase().map {
                    it.mapToRemoteResponse() as AuthorsDataResponse?
                }
            }
            .map {
                it.mapToDomain()
            }
    }


    override fun getAuthorsListFromLocalStorage(): Single<List<AuthorsDomainResponseItem>> {
        return localDataSource.getAuthorsFromDatabase().map {
            it.map { entity ->
                entity.mapToDomain()
            }
        }
    }
}