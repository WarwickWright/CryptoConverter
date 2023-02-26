package com.example.cryptoconverter.viewModels

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoconverter.App
import com.example.cryptoconverter.dataObjects.TimeStampedRatesObjList
import com.example.cryptoconverter.deserializers.Deserializers
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RatesViewModel : ViewModel() {
    private val app = App.app()
    private val deserializers = Deserializers()

    private val _ratesList = MutableLiveData<TimeStampedRatesObjList>()
    fun get(): MutableLiveData<TimeStampedRatesObjList> {
        return _ratesList
    }

    fun updateRatesList(coroutineScope: LifecycleCoroutineScope) {
        viewModelScope.launch(coroutineScope.coroutineContext, CoroutineStart.DEFAULT, block = {
            return@launch withContext(Dispatchers.IO) {
                val jso = app.onlineData.getHttpDataAsString(app.url)
                val ratesObjList = deserializers.getCoinList(jso)
                _ratesList.postValue(ratesObjList)
            }
        })
    }

}