package com.example.sahaelectricalstores.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.adapters.CompanyListAdapter
import com.example.sahaelectricalstores.databinding.FragmentWireBinding
import com.example.sahaelectricalstores.fragmentViewModel.DeliveryDetailViewModel
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Wire : Fragment() {

    private var binding : FragmentWireBinding ?= null
    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_wire,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.setQuantity(1)
        sharedViewModel.setLength(1.0)
        sharedViewModel.setWireColor("error")
        sharedViewModel.updatePrice()

        binding?.wireCompanyList?.adapter = CompanyListAdapter(sharedViewModel)
        binding?.wireCompanyList?.setHasFixedSize(true)

        binding?.apply{
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            wireFragment = this@Wire
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToNext(){
        if(sharedViewModel.company.value==""||sharedViewModel.company.value=="error") {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Error")
                .setMessage("Please Select a Brand")
                .setCancelable(false)
                .setPositiveButton("ok"){_,_->return@setPositiveButton}
                .show()
            return
        }
        findNavController().navigate(R.id.action_wire_to_wireColorQuantity)
    }

    fun cancel(){
        sharedViewModel.reset()
        findNavController().navigate(R.id.action_wire_to_product)
    }

}