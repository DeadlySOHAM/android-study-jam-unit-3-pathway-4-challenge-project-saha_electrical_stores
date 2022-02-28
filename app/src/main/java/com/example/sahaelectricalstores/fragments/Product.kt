package com.example.sahaelectricalstores.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.adapters.ProductListAdapter
import com.example.sahaelectricalstores.databinding.FragmentProductBinding
import com.example.sahaelectricalstores.fragmentViewModel.DeliveryDetailViewModel

class Product : Fragment() {

    private val sharedViewModel : OrderViewModel by activityViewModels()
    private var binding : FragmentProductBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.productListRecyclerView?.adapter = ProductListAdapter(sharedViewModel,this@Product)

        binding?.productListRecyclerView?.setHasFixedSize(true)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToNext(){
        when(sharedViewModel.productId.value) {
            else -> findNavController().navigate(R.id.action_product_to_wattage)
        }
    }

}