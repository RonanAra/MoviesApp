package com.example.moviesapp.features.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.base.BaseViewModel
import com.example.moviesapp.features.usecase.HomeUseCase
import com.example.moviesapp.model.Result
import com.example.moviesapp.utils.ResponseApi
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val homeUseCase = HomeUseCase()

    private val _onSuccesPopular: MutableLiveData<List<Result>> = MutableLiveData()

    val onSuccessPopular: LiveData<List<Result>>
        get() = _onSuccesPopular

    private val _onErrorPopular: MutableLiveData<String> = MutableLiveData()

    val onErrorPopular: LiveData<String>
        get() = _onErrorPopular


    fun getPopular() {
        viewModelScope.launch {
            callApi(
                suspend { homeUseCase.getPopular() },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccesPopular.postValue(
                        result?.filterIsInstance<Result>()
                    )
                }
            )
        }
    }
}

