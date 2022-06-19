package com.assignment.domain.usecase

import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.repository.AuthorsRepository
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetAuthorsListUseCaseTest {

    lateinit var authorRepo: AuthorsRepository

    @Before
    fun setup() {
        authorRepo = mock(AuthorsRepository::class.java)
    }

    @Test
    fun `getAuthors() when internet connected then return listOf Authors`() {

        //arrange
        val auth1 = AuthorsDomainResponseItem(id = 1)
        val auth2 = AuthorsDomainResponseItem(id = 2)
        val listOfAuth = ArrayList<AuthorsDomainResponseItem>().apply {
            add(auth1)
            add(auth2)
        }
        val authDomain = AuthorsDomainResponse(listOfAuth)
        var result: AuthorsDomainResponse
        runBlocking {
            `when`(authorRepo.getAuthorsListFromAPI()).thenReturn(authDomain)
             result = GetAuthorsListUseCase(authorRepo).getAuthors()
        }

        //assert
        assertEquals(authDomain, result)

    }

    @Test
    fun `getAuthors() when internet connected then return empty list`() {

        val authFakeModel = AuthorsDomainResponse()

        var result: AuthorsDomainResponse
        runBlocking {
            `when`(authorRepo.getAuthorsListFromAPI()).thenReturn(authFakeModel)
             result = GetAuthorsListUseCase(authorRepo).getAuthors()
        }

        assertEquals(authFakeModel, result)

    }

    @Test
    fun `getAuthors() when internet disconnected then return list from authors from local storage`() {
        val auth1 = AuthorsDomainResponseItem(id = 1)
        val auth2 = AuthorsDomainResponseItem(id = 2)
        val listOfAuth = ArrayList<AuthorsDomainResponseItem>().apply {
            add(auth1)
            add(auth2)
        }

        val fakeRepo = object : AuthorsRepository {
            override suspend fun getAuthorsListFromAPI(): AuthorsDomainResponse {
                return AuthorsDomainResponse(emptyList())
            }

            override suspend fun getAuthorsListFromLocalStorage(): List<AuthorsDomainResponseItem> {
                return listOfAuth
            }
        }

        var result: List<AuthorsDomainResponseItem>
        runBlocking {
             result = GetAuthorsListUseCase(fakeRepo).getAuthorsFromStorage()

        }
        assertEquals(listOfAuth, result)

    }

    @Test
    fun `getAuthorsFromStorage() return empty list`() {

        val authList = emptyList<AuthorsDomainResponseItem>()
        val fakeRepo = object : AuthorsRepository {
            override suspend fun getAuthorsListFromAPI(): AuthorsDomainResponse {
                return AuthorsDomainResponse(emptyList())
            }

            override suspend fun getAuthorsListFromLocalStorage(): List<AuthorsDomainResponseItem> {
                return authList
            }

            /* override fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse> {
                return Single.just(AuthorsDomainResponse(emptyList()))
            }

            override fun getAuthorsListFromLocalStorage(): Single<List<AuthorsDomainResponseItem>> {
                return Single.just(authList)
            }*/
        }
        var result : List<AuthorsDomainResponseItem>
        runBlocking {
            result = GetAuthorsListUseCase(fakeRepo).getAuthorsFromStorage()
        }
        assertEquals(authList, result)
    }

    @Test
    fun `getAuthorsFromStorage() return listOf Authors`() {
        val list = ArrayList<AuthorsDomainResponseItem>()
        list.add(0, AuthorsDomainResponseItem())
        list.add(1, AuthorsDomainResponseItem())
        list.add(2, AuthorsDomainResponseItem())
        list.add(3, AuthorsDomainResponseItem())

        val fakeRepo = object : AuthorsRepository {
            override suspend fun getAuthorsListFromAPI(): AuthorsDomainResponse {
                return AuthorsDomainResponse(emptyList())
            }

            override suspend fun getAuthorsListFromLocalStorage(): List<AuthorsDomainResponseItem> {
                return list
            }

        }
        var result : List<AuthorsDomainResponseItem>
        runBlocking {
            result = GetAuthorsListUseCase(fakeRepo).getAuthorsFromStorage()
        }
        assertEquals(list, result)
    }
}