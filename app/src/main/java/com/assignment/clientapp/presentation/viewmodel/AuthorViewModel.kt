package com.assignment.clientapp.presentation.viewmodel

import com.assignment.clientapp.presentation.core.BaseViewModel
import com.assignment.clientapp.presentation.core.wrapper.StateLiveData
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.model.PostsDomainResponseItem
import com.assignment.domain.usecase.GetAuthorsListUseCase
import com.assignment.domain.usecase.GetPostsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
open class AuthorViewModel @Inject constructor(
    private val authorsUseCase: GetAuthorsListUseCase,
    private val postsUseCase: GetPostsListUseCase
) :
    BaseViewModel() {

    val authorsLiveData by lazy { StateLiveData<List<AuthorsDomainResponseItem>?>() }
    val postsLiveData by lazy { StateLiveData<List<PostsDomainResponseItem>?>() }

    fun getAuthorsList() {
        compositeDisposable.add(
            authorsUseCase
                .getAuthors()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    authorsLiveData.postLoading()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    authorsLiveData.postSuccess(response.authorsDomainResponse)
                },
                    { error ->
                        authorsLiveData.postError(error)
                        error.printStackTrace()
                    })
        )
    }

    fun getAuthorsFromStorage() {
        compositeDisposable.add(
            authorsUseCase.getAuthorsFromStorage()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    authorsLiveData.postLoading()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    authorsLiveData.postSuccess(response)
                },
                    { error ->
                        authorsLiveData.postError(error)
                        error.printStackTrace()
                    })
        )
    }


    fun getPostsForAuthor(authorId: String) {
        compositeDisposable.add(
            postsUseCase.getPostsForUser(authorId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    postsLiveData.postLoading()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    postsLiveData.postSuccess(response.postsDomainResponse)
                },
                    { error ->
                        postsLiveData.postError(error)
                        error.printStackTrace()
                    })
        )
    }


}