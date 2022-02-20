package com.assignment.clientapp.presentation.viewmodel

import com.assignment.clientapp.presentation.core.BaseViewModel
import com.assignment.clientapp.presentation.core.wrapper.StateLiveData
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.usecase.GetAuthorsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
open class AuthorViewModel @Inject constructor(
    private val authorsUseCase: GetAuthorsListUseCase,
) :
    BaseViewModel() {

    val authorsLiveData by lazy { StateLiveData<List<AuthorsDomainResponseItem>?>() }

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


}