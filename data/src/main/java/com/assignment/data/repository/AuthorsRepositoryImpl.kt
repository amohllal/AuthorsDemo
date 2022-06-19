package com.assignment.data.repository

import com.assignment.data.local.AuthorDAO
import com.assignment.data.mapper.mapToDomain
import com.assignment.data.mapper.mapToDomainResponse
import com.assignment.data.mapper.mapToEntity
import com.assignment.data.mapper.mapToRemoteResponse
import com.assignment.data.model.remote.author.AuthorsDataResponse
import com.assignment.data.remote.AuthorAPI
import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.repository.AuthorsRepository
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.selects.SelectBuilder
import kotlinx.coroutines.selects.select
import javax.inject.Inject

class AuthorsRepositoryImpl @Inject constructor
    (
    private val remoteDataSource: AuthorAPI,
    private val localDataSource: AuthorDAO
) : AuthorsRepository {
    override suspend fun getAuthorsListFromAPI(): AuthorsDomainResponse {
        val response = remoteDataSource.getAuthors()
        return if (response.isSuccessful){
            localDataSource.saveListOfAuthors(response.body()?.map { it.mapToEntity() }!!)
            response.body()!!.mapToDomain()
        }else{
            localDataSource.getAuthorsFromDatabase().mapToRemoteResponse().mapToDomainResponse()
        }

    }

    override suspend fun getAuthorsListFromLocalStorage(): List<AuthorsDomainResponseItem> {
        return localDataSource.getAuthorsFromDatabase().mapToRemoteResponse().map {
            it.mapToEntity().mapToDomain()
        }
    }
}