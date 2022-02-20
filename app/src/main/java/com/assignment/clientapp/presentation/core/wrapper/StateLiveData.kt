package com.assignment.clientapp.presentation.core.wrapper

class StateLiveData<T> : SingleLiveEvent<DataStatus<T>?>() {

    fun postLoading() {
        postValue(DataStatus<T>().loading())
    }

    fun postSuccess(data: T) {
        postValue(DataStatus<T>().success(data))
    }

    fun postError(throwable: Throwable?) {
        postValue(DataStatus<T>().error(throwable))
        //value =
    }


}