package com.example.moviesapp.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.utils.Command

abstract class BaseFragment: Fragment() {
    abstract var command: MutableLiveData<Command>
}