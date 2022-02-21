package com.assignment.domain.usecase

import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.repository.AuthorsRepository
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAuthorsListUseCaseTest {


    @Test
    fun `getAuthors() when internet connected then return listOf Authors`() {

        val auth1 = AuthorsDomainResponseItem(id = 1)
        val auth2 = AuthorsDomainResponseItem(id = 2)
        val listOfAuth = ArrayList<AuthorsDomainResponseItem>().apply {
            add(auth1)
            add(auth2)
        }
        val authDomain = AuthorsDomainResponse(listOfAuth)

        val fakeRepo = object : AuthorsRepository {
            override fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse> {
                return Single.just(authDomain)
            }

            override fun getAuthorsListFromLocalStorage(): Single<List<AuthorsDomainResponseItem>> {
                return Single.just(ArrayList())
            }
        }
        val result = GetAuthorsListUseCase(fakeRepo).getAuthors().blockingGet()

        assertEquals(authDomain, result)

    }

    @Test
    fun `getAuthors() when internet connected then return empty list`() {

        val list = ArrayList<AuthorsDomainResponseItem>()
        val fakeRepo = object : AuthorsRepository {
            override fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse> {
                return Single.just(AuthorsDomainResponse(emptyList()))
            }

            override fun getAuthorsListFromLocalStorage(): Single<List<AuthorsDomainResponseItem>> {
                return Single.just(ArrayList())
            }
        }
        val result = GetAuthorsListUseCase(fakeRepo).getAuthors().blockingGet()

        val expected = AuthorsDomainResponse(list)
        assertEquals(expected, result)

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
            override fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse> {
                return Single.just(AuthorsDomainResponse(emptyList()))
            }

            override fun getAuthorsListFromLocalStorage(): Single<List<AuthorsDomainResponseItem>> {
                return Single.just(listOfAuth)
            }
        }

        val result = GetAuthorsListUseCase(fakeRepo).getAuthorsFromStorage().blockingGet()
        assertEquals(listOfAuth, result)

    }

    @Test
    fun `getAuthorsFromStorage() return empty list`() {

        val fakeRepo = object : AuthorsRepository {
            override fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse> {
                return Single.just(AuthorsDomainResponse(emptyList()))
            }

            override fun getAuthorsListFromLocalStorage(): Single<List<AuthorsDomainResponseItem>> {
                return Single.just(ArrayList())
            }
        }
        val result = GetAuthorsListUseCase(fakeRepo).getAuthorsFromStorage().blockingGet()

        val expected = emptyList<AuthorsDomainResponseItem>()
        assertEquals(expected, result)
    }

    @Test
    fun `getAuthorsFromStorage() return listOf Authors`() {
        val list = ArrayList<AuthorsDomainResponseItem>()
        list.add(0, AuthorsDomainResponseItem())
        list.add(1, AuthorsDomainResponseItem())
        list.add(2, AuthorsDomainResponseItem())
        list.add(3, AuthorsDomainResponseItem())

        val fakeRepo = object : AuthorsRepository {
            override fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse> {
                return Single.just(AuthorsDomainResponse(emptyList()))
            }

            override fun getAuthorsListFromLocalStorage(): Single<List<AuthorsDomainResponseItem>> {
                return Single.just(list)
            }
        }
        val result = GetAuthorsListUseCase(fakeRepo).getAuthorsFromStorage().blockingGet()

        assertEquals(list, result)
    }
}