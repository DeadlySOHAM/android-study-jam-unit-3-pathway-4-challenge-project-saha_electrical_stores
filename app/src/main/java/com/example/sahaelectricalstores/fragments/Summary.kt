package com.example.sahaelectricalstores.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.databinding.FragmentSummaryBinding
import com.example.sahaelectricalstores.fragmentViewModel.DeliveryDetailViewModel
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel

class Summary : Fragment() {

    private var binding : FragmentSummaryBinding?= null
    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_summary,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragmentSummary = this@Summary
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToNext(){
        findNavController().navigate(R.id.action_summary_to_deleviryDetails)
    }

    fun cancel(){
        sharedViewModel.reset()
        findNavController().navigate(R.id.action_summary_to_product)
    }

}