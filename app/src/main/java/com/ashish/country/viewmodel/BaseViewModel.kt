package com.ashish.country.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T> : ViewModel() {

    protected val uiState: MutableLiveData<T> = MutableLiveData()
    fun uiState(): LiveData<T> = uiState
}