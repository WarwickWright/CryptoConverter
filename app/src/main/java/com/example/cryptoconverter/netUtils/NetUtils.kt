package com.example.cryptoconverter.netUtils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoconverter.viewModels.NetStatus

class NetUtils(private val netStatus: NetStatus, context: AppCompatActivity) {

    private lateinit var netCallback: ConnectivityManager.NetworkCallback
    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var isConnected: Boolean = false

    fun setIsNetworkAvailable() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            isConnected = connectivityManager.activeNetworkInfo != null
            netStatus.isConnected.value = isConnected
        }
        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val requestBuilder: NetworkRequest.Builder = NetworkRequest.Builder()
            requestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            requestBuilder.addCapability(NetworkCapabilities.NET_CAPABILITY_SUPL)
            val request = requestBuilder.build()
            val netAvailable = false
            netCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isConnected = true
                    netStatus.isConnected.postValue(true)
                }
                override fun onLost(network: Network) {
                    isConnected = false
                    netStatus.isConnected.postValue(false)
                }
                override fun onLosing(network: Network, maxMsToLive: Int) {
                    isConnected = false
                    netStatus.isConnected.postValue(false)
                }
                override fun onUnavailable() {
                    //!netAvailable condition needed as for some reason onUnavailable gets called after onAvailable when network is available
                    if(!netAvailable) {
                        isConnected = false
                        netStatus.isConnected.postValue(false)
                    }
                }
            }
            //Requires 'android.permission.CHANGE_NETWORK_STATE'
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                connectivityManager.requestNetwork(request, netCallback, 100)
            }
            connectivityManager.registerDefaultNetworkCallback(netCallback)
        }
    }
}