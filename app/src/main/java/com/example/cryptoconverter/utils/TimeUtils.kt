package com.example.cryptoconverter.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    private val sdf = SimpleDateFormat("yyyy:MM:dd:HH:mm:ss", Locale.getDefault())

    fun getFriendlyDate(date: Date) : String {
        return sdf.format(date)
    }
}