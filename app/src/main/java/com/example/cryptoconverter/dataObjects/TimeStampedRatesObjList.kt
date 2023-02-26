package com.example.cryptoconverter.dataObjects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeStampedRatesObjList(var friendlyDateTime: String, var ratesObjList: MutableList<RatesObj>) : Parcelable