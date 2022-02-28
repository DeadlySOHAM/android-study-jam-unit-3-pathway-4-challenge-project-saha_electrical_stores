package com.example.sahaelectricalstores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel

class CompanyListAdapter(
    private val binding : OrderViewModel
) : RecyclerView.Adapter<CompanyListAdapter.CompanyListViewHolder>() {

    private var data : List<String> = binding.getCompanyData()

    class CompanyListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val button : RadioButton = view.findViewById(R.id.radioButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyListViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.company_list_recycler_view_holder,parent,false)
        return CompanyListViewHolder(adapterLayout)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CompanyListViewHolder, position: Int) {

        val cmp = data[position]
        holder.button.text = cmp

        if(holder.button.text.toString() == binding.company.value) {
            holder.button.isChecked = true
            binding.buttonData.value = holder.button
        }
        else if(binding.company.value == "error" || binding.company.value == "")
//          default selection of Company
            if(position == 0) {
                holder.button.isChecked = true
                binding.setCompany(holder.button.text.toString())
                binding.buttonData.value = holder.button
                binding.updatePrice()
            }

        holder.button.setOnClickListener{
            binding.buttonData.value?.isChecked = false
            holder.button.isChecked = true
            binding.setCompany(holder.button.text.toString())
            binding.buttonData.value = holder.button
            binding.updatePrice()
        }
    }
}
