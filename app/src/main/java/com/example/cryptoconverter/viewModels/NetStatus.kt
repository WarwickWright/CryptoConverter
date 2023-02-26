package com.example.cryptoconverter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetStatus : ViewModel() {
    private val _isConnected = MutableLiveData(false)
    val isConnected: MutableLiveData<Boolean>
    get() = _isConnected

}