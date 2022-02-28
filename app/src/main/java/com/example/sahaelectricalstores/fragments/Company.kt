package com.example.sahaelectricalstores.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.adapters.CompanyListAdapter
import com.example.sahaelectricalstores.databinding.FragmentCompanyBinding
import com.example.sahaelectricalstores.fragmentViewModel.DeliveryDetailViewModel
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Company : Fragment() {

    private var binding : FragmentCompanyBinding ?= null
    private val sharedViewModel : OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_company,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initializing for use
        sharedViewModel.setQuantity(1)

        /** although length dosen't matter but it is taken into account for calculation of wire
         * which was interferring with other's price , so reseting is necessary
         */
        sharedViewModel.setLength(1.0)
        sharedViewModel.updatePrice()

        binding?.companyList?.adapter = CompanyListAdapter(sharedViewModel)

        binding?.companyList?.setHasFixedSize(true)

        binding?.quantityValue?.doOnTextChanged{ text, _, _ , _ -> quantityEventHandler(text.toString())  }

        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            companyFragment = this@Company
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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
                sharedViewModel.setQuantity(1)
                sharedViewModel.updatePrice()
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
        if(sharedViewModel.company.value=="error" || sharedViewModel.company.value == "") {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Error")
                .setMessage("Please Select a Brand")
                .setCancelable(false)
                .setPositiveButton("ok"){_,_->return@setPositiveButton}
                .show()
            return
        }
        findNavController().navigate(R.id.action_company_to_summary)
    }

    fun cancel(){
        sharedViewModel.reset()
        findNavController().navigate(R.id.action_company_to_product)
    }

}