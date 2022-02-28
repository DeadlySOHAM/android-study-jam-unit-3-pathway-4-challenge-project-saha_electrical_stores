package com.example.sahaelectricalstores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel
import com.example.sahaelectricalstores.fragments.Wattage

class WattListAdapter(
    private val sharedViewModel: OrderViewModel ,
    private val binding : Wattage ,
) : RecyclerView.Adapter<WattListAdapter.WattViewHolder>(){

    private val data : List<Double> = sharedViewModel.getWattageData()

    class WattViewHolder(val view: View ): ViewHolder(view){
        val button : Button = view.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WattViewHolder {
        val adaperLayout = LayoutInflater.from(parent.context).inflate(R.layout.watt_recycler_view_holder,parent,false)
        return WattViewHolder(adaperLayout)
    }

    override fun onBindViewHolder(holder: WattViewHolder, position: Int){
        var d = data[position]
        var unit = "watts"

        if(sharedViewModel.productId.value == 4)  holder.button.text = d.toString() + " mm"
        else holder.button.text = d.toInt().toString() + " " + unit

        if (d<1)     holder.button.text = "0 Power"

        holder.button.setOnClickListener{
            // reseting
            sharedViewModel.setCompany("")
            sharedViewModel.setWattage(d.toDouble())
            binding.goToNext()
        }
    }

    override fun getItemCount() = data.size

}