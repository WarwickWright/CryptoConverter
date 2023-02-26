package com.example.cryptoconverter.netUtils

import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class OnlineData {

    fun getHttpDataAsString(url: URL): JSONObject {
        var httpURLConnection: HttpURLConnection? = null
        val bufferedReader: BufferedReader
        val sb: StringBuilder

        try {
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = false
            httpURLConnection.useCaches = true
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.readTimeout = 15 * 1000
            httpURLConnection.setRequestProperty("Accept-Encoding", "x-www-form-urlencoded")
            httpURLConnection.connect()
            bufferedReader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
            sb = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach { line ->
                    sb.append(line)
                }
            }
            bufferedReader.close()
            httpURLConnection.inputStream?.close()
            return JSONObject(sb.toString())
        } catch (e: IOException) {
            e.printStackTrace()
            return JSONObject()
        } finally {
            httpURLConnection?.disconnect()
        }
    }
}