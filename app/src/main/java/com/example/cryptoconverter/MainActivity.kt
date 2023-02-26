package com.example.cryptoconverter

import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import com.example.cryptoconverter.netUtils.NetUtils
import com.example.cryptoconverter.ui.ConversionListFragment
import com.example.cryptoconverter.viewModels.DateViewModel
import com.example.cryptoconverter.viewModels.NetStatus
import com.example.cryptoconverter.viewModels.RatesViewModel

class MainActivity : AppCompatActivity() {

    private val app = App.app()
    private lateinit var netUtils: NetUtils
    private lateinit var conversionListFragment: ConversionListFragment
    private var firstRun = true
    private lateinit var lblTime: TextView
    private lateinit var lblTarget: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //app.url = URL(END_POINT + "access_key=" + API_KEY + "&" + "target=" + DEFAULT_TARGET)// This is default url value se in unit App
        netStatus = ViewModelProvider(this).get(NetStatus::class.java)
        ratesViewModel = ViewModelProvider(this).get(RatesViewModel::class.java)
        dateViewModel = ViewModelProvider(this).get(DateViewModel::class.java)
        netUtils = NetUtils(netStatus, this)
        netUtils.setIsNetworkAvailable()

        lblTime = findViewById(R.id.lblTime)
        lblTarget = findViewById(R.id.lblTarget)

        netStatus.isConnected.observe(this) { isConnected ->
            if(isConnected) {
                ratesViewModel.updateRatesList(lifecycle.coroutineScope)
            }
            else {
                if(!firstRun) {
                    Toast.makeText(this, getString(R.string.toast_no_net), Toast.LENGTH_LONG).show()
                    firstRun = false
                }
            }
        }

        ratesViewModel.get().observe(this) { TimeStampedRatesObjList ->
            dateViewModel.get().value = TimeStampedRatesObjList.friendlyDateTime
            val bundle = Bundle()
            bundle.putParcelableArrayList("ratesObjList", TimeStampedRatesObjList.ratesObjList as ArrayList<out Parcelable>)
            conversionListFragment = ConversionListFragment()
            conversionListFragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.lytListContainer, conversionListFragment, "ConversionListFragment").commit()
        }

        dateViewModel.get().observe(this) { friendlyDateTime ->
            lblTime.text = friendlyDateTime
            lblTarget.text = app.targetCurrency
        }

    }

    companion object {
        private lateinit var netStatus: NetStatus
        private lateinit var ratesViewModel: RatesViewModel
        private lateinit var dateViewModel: DateViewModel
    }

}