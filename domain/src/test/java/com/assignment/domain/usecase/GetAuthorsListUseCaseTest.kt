package com.assignment.domain.usecase

import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.repository.AuthorsRepository
import io.reactivex.Single
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
        `when`(authorRepo.getAuthorsListFromAPI()).thenReturn(Single.just(authDomain))

        //act
        val result = GetAuthorsListUseCase(authorRepo).getAuthors().blockingGet()

        //assert
        assertEquals(authDomain, result)

    }

    @Test
    fun `getAuthors() when internet connected then return empty list`() {

        val authFakeModel = AuthorsDomainResponse()

        `when`(authorRepo.getAuthorsListFromAPI()).thenReturn(Single.just(authFakeModel))

        val result = GetAuthorsListUseCase(authorRepo).getAuthors().blockingGet()

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

        val authList = emptyList<AuthorsDomainResponseItem>()
        val fakeRepo = object : AuthorsRepository {
            override fun getAuthorsListFromAPI(): Single<AuthorsDomainResponse> {
                return Single.just(AuthorsDomainResponse(emptyList()))
            }

            override fun getAuthorsListFromLocalStorage(): Single<List<AuthorsDomainResponseItem>> {
                return Single.just(authList)
            }
        }
        val result = GetAuthorsListUseCase(fakeRepo).getAuthorsFromStorage().blockingGet()

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