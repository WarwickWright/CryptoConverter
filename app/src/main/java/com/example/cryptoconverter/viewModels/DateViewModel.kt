package com.example.cryptoconverter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DateViewModel : ViewModel() {
    private val _selected = MutableLiveData<String>()
    fun get(): MutableLiveData<String> {
        return _selected
    }
}