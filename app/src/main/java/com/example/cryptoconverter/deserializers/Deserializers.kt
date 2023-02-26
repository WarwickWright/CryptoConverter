package com.example.cryptoconverter.deserializers

import com.example.cryptoconverter.dataObjects.RatesObj
import com.example.cryptoconverter.dataObjects.TimeStampedRatesObjList
import com.example.cryptoconverter.utils.TimeUtils
import org.json.JSONObject
import java.util.*

class Deserializers {

    fun getCoinList(jso: JSONObject): TimeStampedRatesObjList {
        val returnVal = TimeStampedRatesObjList("", mutableListOf())
        val timeUtils = TimeUtils()
        if(jso.getBoolean("success")) {
            val jsonHashMap = jso.getJSONObject("rates").keys()
            val date = Date(jso.getInt("timestamp").toLong().times(1000))
            returnVal.friendlyDateTime = timeUtils.getFriendlyDate(date)
            val jso = jso.get("rates") as JSONObject
            for (map in jsonHashMap) {
                returnVal.ratesObjList.add(RatesObj(map.toString(), jso.getDouble(map.toString())))
            }
        }
        return returnVal
    }

}