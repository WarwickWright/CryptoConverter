package com.example.cryptoconverter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoconverter.App
import com.example.cryptoconverter.constants.API_KEY
import com.example.cryptoconverter.constants.END_POINT
import com.example.cryptoconverter.dataObjects.RatesObj
import com.example.cryptoconverter.databinding.FragmentConversionItemBinding
import com.example.cryptoconverter.viewModels.RatesViewModel
import java.net.URL

class RatesAdapter(
    private val ratesObjList: MutableList<RatesObj>,
    private val ratesViewModel: RatesViewModel,
    private val coroutineScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<RatesAdapter.ViewHolder>(), View.OnClickListener {

    private val app = App.app()

    override fun onClick(v: View?) {
        val targetCurrency = (v?.tag as RatesObj).key
        app.url = URL(END_POINT + "access_key=" + API_KEY + "&" + "target=" + targetCurrency)
        app.targetCurrency = targetCurrency
        ratesViewModel.updateRatesList(coroutineScope)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentConversionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ratesObjList[position]
        holder.itemView.tag = item
        holder.itemView.setOnClickListener(this)
        holder.lblKey.text = item.key
        holder.lblValue.text = item.value.toString()
    }

    override fun getItemCount(): Int = ratesObjList.size

    inner class ViewHolder(binding: FragmentConversionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val lblKey: TextView = binding.lblKey
        val lblValue: TextView = binding.lblValue

        override fun toString(): String {
            return super.toString() + " '" + lblValue.text + "'"
        }
    }
}