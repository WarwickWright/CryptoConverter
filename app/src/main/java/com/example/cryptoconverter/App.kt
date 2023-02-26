package com.example.cryptoconverter

import android.app.Application
import com.example.cryptoconverter.constants.API_KEY
import com.example.cryptoconverter.constants.DEFAULT_TARGET
import com.example.cryptoconverter.constants.END_POINT
import com.example.cryptoconverter.netUtils.OnlineData
import java.net.URL

class App: Application() {
    //var rpcCallInProgress = false//Open End
    var url: URL = URL(END_POINT + "access_key=" + API_KEY + "&" + "target=" + DEFAULT_TARGET)
    var onlineData = OnlineData()
    var targetCurrency: String = DEFAULT_TARGET

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        lateinit var app: App
        @JvmStatic
        fun app(): App {
            return app
        }
    }
}