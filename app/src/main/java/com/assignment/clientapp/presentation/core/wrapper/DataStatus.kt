package com.assignment.clientapp.presentation.core.wrapper


class DataStatus<T> {

    var status: Status? = null

    var data: T? = null

    var error: Throwable? = null

    fun loading(): DataStatus<T> {
        status = Status.LOADING
        data = null
        error = null
        return this
    }

    fun success(data: T): DataStatus<T> {
        status = Status.SUCCESS
        this.data = data
        error = null
        return this
    }

    fun error(error: Throwable?): DataStatus<T> {
        status = Status.ERROR
        data = null
        this.error = error
        return this
    }


    enum class Status {
        SUCCESS, ERROR, LOADING
    }


}