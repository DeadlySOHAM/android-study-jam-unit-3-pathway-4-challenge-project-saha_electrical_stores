package com.example.sahaelectricalstores.fragmentViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeliveryDetailViewModel : ViewModel() {

    private val _name = MutableLiveData<String>("error")
    val name : LiveData<String> get() = _name

    private val _address = MutableLiveData<String>("error")
    val address : LiveData<String> get() = _address

    private val _phoneNumber = MutableLiveData<String>("error")
    val phoneNumber : LiveData<String> get() = _phoneNumber

    private val _landmark = MutableLiveData<String>("error")
    val landmark : LiveData<String> get() = _landmark

    private val _pincode = MutableLiveData<String>("error")
    val pincode : LiveData<String> get() = _pincode


    fun setAddress(x:String){
        _address.value = x
    }

    fun setName(x:String){
        _name.value = x
    }

    fun setPhoneNumber(x:String){
        _phoneNumber.value = x
    }

    fun setLandmark(x:String){
        _landmark.value=x
    }

    fun setPincode(x:String){
        _pincode.value=x
    }

    fun isOk():Boolean{
        return when("error"){
            _name.value -> false
            _address.value -> false
            _phoneNumber.value -> false
            _landmark.value -> false
            _pincode.value -> false
            else -> true
        }
    }

}