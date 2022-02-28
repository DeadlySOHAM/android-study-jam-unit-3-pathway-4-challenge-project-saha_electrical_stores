package com.example.sahaelectricalstores.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.adapters.WireColorQuantityListAdapter
import com.example.sahaelectricalstores.databinding.FragmentWireColorQuantityBinding
import com.example.sahaelectricalstores.fragmentViewModel.DeliveryDetailViewModel
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WireColorQuantity : Fragment() {

    private var binding : FragmentWireColorQuantityBinding ?= null
    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_wire_color_quantity,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setting default length to 5 as soon as the view is created as it is 1 in viewModel
        sharedViewModel.setLength(5.0)
        sharedViewModel.updatePrice()

        binding?.colorNameList?.adapter = WireColorQuantityListAdapter(sharedViewModel)
        binding?.colorNameList?.setHasFixedSize(true)

        binding?.quantityInput?.doOnTextChanged { text, _, _ , _ -> quantityEventHandler(text.toString()) }
        binding?.lengthInput?.doOnTextChanged { text, _, _ , _ -> lengthEventHandler(text.toString()) }

        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragmentWireColorQuantity = this@WireColorQuantity
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun lengthEventHandler(x:String){
        if(x==""){
            binding?.lengthValueInputLayout?.isErrorEnabled = false
            sharedViewModel.setLength(5.0)
            sharedViewModel.updatePrice()
            return
        }
        val v = x.toDouble()
        when {
            v<1 -> {
                binding?.lengthValueInputLayout?.isErrorEnabled = true
                binding?.lengthValueInputLayout?.error = "This Cannot be Zero .\nYou Have to Order atleast 1 ."
                return
            }
            v>499 -> {
                binding?.lengthValueInputLayout?.isErrorEnabled = true
                binding?.lengthValueInputLayout?.error = "This is too much for house Hold ,\nare you sure ?"
                sharedViewModel.setLength(v)
                sharedViewModel.updatePrice()
            }
            else -> {
                binding?.lengthValueInputLayout?.isErrorEnabled = false
                sharedViewModel.setLength(v)
                sharedViewModel.updatePrice()
            }
        }
    }

    private fun quantityEventHandler(x:String){
        if(x==""){
            binding?.quantityValueInputLayout?.isErrorEnabled = false
            sharedViewModel.setQuantity(1)
            sharedViewModel.updatePrice()
            return
        }
        val v = x.toInt()
        when {
            v<=0 -> {
                binding?.quantityValueInputLayout?.isErrorEnabled = true
                binding?.quantityValueInputLayout?.error = "This Cannot be Zero .\nYou Have to Order atleast 1 ."
                return
            }
            v>49 -> {
                binding?.quantityValueInputLayout?.isErrorEnabled = true
                binding?.quantityValueInputLayout?.error = "This is too much for house Hold ,\nare you sure ?"
                sharedViewModel.setQuantity(v)
                sharedViewModel.updatePrice()
            }
            else -> {
                binding?.quantityValueInputLayout?.isErrorEnabled = false
                sharedViewModel.setQuantity(v)
                sharedViewModel.updatePrice()
            }
        }
    }

    fun goToNext(){
        if(sharedViewModel.colorName.value=="error" || sharedViewModel.colorName.value == "") {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Error")
                .setMessage("Please Select a Color")
                .setCancelable(false)
                .setPositiveButton("ok"){_,_->return@setPositiveButton}
                .show()
            return
        }
        else if(sharedViewModel.quantity.value!! * sharedViewModel.length.value!! > 999)
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("High Quantity !!")
                .setMessage("This is too much for house Hold , are you sure ?")
                .setCancelable(false)
                .setNegativeButton("No"){_,_->return@setNegativeButton}
                .setPositiveButton("Yes"){_,_->return@setPositiveButton findNavController().navigate(R.id.action_wireColorQuantity_to_summary)}
                .show()
        else  findNavController().navigate(R.id.action_wireColorQuantity_to_summary)
    }

    fun cancel(){
        sharedViewModel.reset()
        findNavController().navigate(R.id.action_wireColorQuantity_to_product)
    }

}