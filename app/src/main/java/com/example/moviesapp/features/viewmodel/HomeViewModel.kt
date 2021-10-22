package com.example.moviesapp.features.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.features.usecase.HomeUseCase
import com.example.moviesapp.model.Result
import com.example.moviesapp.utils.ResponseApi
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val homeUseCase = HomeUseCase()

    private val _onSuccesPopular: MutableLiveData<List<Result>> = MutableLiveData()

    val onSuccessPopular: LiveData<List<Result>>
        get() = _onSuccesPopular

    private val _onErrorPopular: MutableLiveData<String> = MutableLiveData()

    val onErrorPopular: LiveData<String>
        get() = _onErrorPopular


    fun getPopular() {
        viewModelScope.launch {
            when (val responseApi = homeUseCase.getPopular()) {
                is ResponseApi.Success -> {
                    val result = responseApi.data as List<*>
                    _onSuccesPopular.postValue(
                        result.filterIsInstance<Result>()
                    )

                }
                is ResponseApi.Error -> {
                    _onErrorPopular.postValue(responseApi.message)
                }
            }
        }
    }
}
