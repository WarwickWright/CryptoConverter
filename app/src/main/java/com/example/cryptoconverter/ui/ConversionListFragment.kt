package com.example.cryptoconverter.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoconverter.R
import com.example.cryptoconverter.dataObjects.RatesObj
import com.example.cryptoconverter.viewModels.RatesViewModel

class ConversionListFragment : Fragment() {

    private val ratesViewModel: RatesViewModel by activityViewModels()
    private lateinit var rootView: RecyclerView
    private var ratesObjList = mutableListOf<RatesObj>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            ratesObjList = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                bundle.getParcelableArrayList("ratesObjList", RatesObj::class.java)!!
            } else {
                bundle.getParcelableArrayList("ratesObjList")!!
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(R.layout.fragment_conversion_list, container, false) as RecyclerView
        with(rootView) {
            layoutManager = LinearLayoutManager(context)
            adapter = RatesAdapter(ratesObjList, ratesViewModel, requireActivity().lifecycle.coroutineScope)
        }
        return rootView
    }
}