package com.example.sahaelectricalstores.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.adapters.WattListAdapter
import com.example.sahaelectricalstores.dataSource.FilamentBulbDataSource
import com.example.sahaelectricalstores.dataSource.LedBulbDataSource
import com.example.sahaelectricalstores.dataSource.TubeLightListDataSource
import com.example.sahaelectricalstores.dataSource.WiresDataSource
import com.example.sahaelectricalstores.databinding.FragmentWattageBinding
import com.example.sahaelectricalstores.fragmentViewModel.DeliveryDetailViewModel
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel

class Wattage : Fragment() {

    private var binding : FragmentWattageBinding ?= null
    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_wattage,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.wattList?.adapter = WattListAdapter(sharedViewModel,this@Wattage)

        if (sharedViewModel.productId.value == 4) (activity as AppCompatActivity).supportActionBar?.title = "Wire Millimeter"

        binding?.wattList?.setHasFixedSize(true)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sharedViewModel.setQuantity(1)
        binding = null
    }

    fun goToNext(){
        sharedViewModel.updatePrice()
        when(sharedViewModel.productId.value) {
            4 -> findNavController().navigate(R.id.action_wattage_to_wire)
            else -> findNavController().navigate(R.id.action_wattage_to_company)
        }
    }

}