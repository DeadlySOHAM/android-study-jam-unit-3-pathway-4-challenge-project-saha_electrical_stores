package com.example.sahaelectricalstores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel

class WireColorQuantityListAdapter(
    private val binding : OrderViewModel ,
): RecyclerView.Adapter<WireColorQuantityListAdapter.WireViewHolder>(){

    val data = binding.getWireColorData()

    class WireViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val colorName : RadioButton = view.findViewById(R.id.color_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WireViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.wire_color_quantity_view_holder,parent,false)
        return WireViewHolder(adapterLayout)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: WireViewHolder, position: Int) {

        val name = data[position]
        holder.colorName.text = name

        if(holder.colorName.text.toString() == binding.colorName.value) {
            holder.colorName.isChecked = true
            binding.buttonData.value = holder.colorName
        }
        else if(binding.colorName.value.equals("error")||binding.colorName.value.equals(""))
//            default color selection
            if(position==0){
                holder.colorName.isChecked = true
                binding.setWireColor(holder.colorName.text.toString())
                binding.buttonData.value = holder.colorName
                binding.updatePrice()
            }

        holder.colorName.setOnClickListener{

            binding.buttonData.value?.isChecked = false
            holder.colorName.isChecked = true
            binding.buttonData.value = holder.colorName

            binding.setWireColor(holder.colorName.text.toString())
            binding.updatePrice()
        }
    }
}