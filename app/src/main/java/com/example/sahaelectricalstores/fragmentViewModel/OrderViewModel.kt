package com.example.sahaelectricalstores.fragmentViewModel

import android.widget.RadioButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sahaelectricalstores.dataSource.FilamentBulbDataSource
import com.example.sahaelectricalstores.dataSource.LedBulbDataSource
import com.example.sahaelectricalstores.dataSource.TubeLightListDataSource
import com.example.sahaelectricalstores.dataSource.WiresDataSource

class OrderViewModel : ViewModel() {

    private var _productId = MutableLiveData<Int>(-404)
    val productId : LiveData<Int> get() = _productId

    private var _productName = MutableLiveData<String>("error")
    val productName : LiveData<String> get() = _productName

    private var _watt = MutableLiveData<Double>(-404.0)
    val watt : LiveData<Double> get() = _watt

    private var _company = MutableLiveData<String>("error")
    val company : LiveData<String> get() = _company

    private var _colorName = MutableLiveData<String>("error")
    val colorName : LiveData<String> get() = _colorName

    // to hold selected Radio button from Company list and Color list , this help in deselecting previously selected option
    val buttonData = MutableLiveData<RadioButton>()

    private var _length = MutableLiveData<Double>(-404.0)
    val length : LiveData<Double> get() = _length

    private var _price = MutableLiveData<Double>(-404.0)
    val price : LiveData<Double> get() = _price

    private var _quantity = MutableLiveData<Int>(-404)
    val quantity : LiveData<Int> get() = _quantity

    init {
        _productName.value = "error"
        _productId.value = -404
        reset()    }

    fun reset(){
        _watt.value = -404.0
        _price.value = -404.0
        _quantity.value = -404
        _length.value = -404.0
        _company.value = "error"
        _colorName.value = "error"
    }

    fun productSelected(id:Int,name : String){
        _productName.value = name
        _productId.value = id
    }

    fun setWattage(x: Double){
        _watt.value = x
    }

    fun setCompany(x:String){
        _company.value = x
    }

    fun setQuantity(x: Int ){
            _quantity.value = x
    }

    fun setWireColor(x:String){
        _colorName.value = x
    }

    fun setLength(x : Double){
        _length.value = x
    }

    fun updatePrice(){
        var MRP = 1280000000.00
        val GST = 0.28
        when(_productId.value){
            1 -> {
                for ( x in TubeLightListDataSource.tubeList)
                    if (_watt.value?.equals(x.watt) == true && _company.value?.equals(x.company) == true )
                            MRP = x.cost+0.0
            }
            2 -> {
                for ( x in LedBulbDataSource.lb_blb)
                    if (_watt.value?.equals(x.watt) == true && _company.value?.equals(x.company) == true )
                            MRP = x.cost+0.0
            }
            3 -> {
                for ( x in FilamentBulbDataSource.flm_blb)
                    if (_watt.value?.equals(x.watt) == true && _company.value?.equals(x.company) == true )
                            MRP = x.cost+0.0
            }
            4 -> {
                for ( x in WiresDataSource.wireData)
                    if (_watt.value?.equals(x.mm) == true && _company.value?.equals(x.company) == true && (_colorName.value?.equals(x.colour)==true || _colorName.value?.equals("error")==true))
                        if (MRP > x.cost)    MRP = x.cost + 0.0
            }
            else -> MRP = 1280000000.00
        }
        _price.value = ( MRP + MRP*GST ) * ( _quantity.value!! * _length.value!!)
    }

    fun getWattageData(): List<Double> {

        val list : MutableList<Double> = mutableListOf()

        when(productId.value){
            1 -> {
                for ( x in TubeLightListDataSource.tubeList)
                    list.add(x.watt)
            }
            2 -> {
                for ( x in LedBulbDataSource.lb_blb)
                    list.add(x.watt)
            }
            3 -> {
                for ( x in FilamentBulbDataSource.flm_blb)
                    list.add(x.watt)
            }
            4 -> {
                for ( x in WiresDataSource.wireData)
                    list.add(x.mm)
            }
            else -> list.add(-404.0)
        }

        // removing duplicates and returning
        return list.toSet().toList().sorted() as MutableList<Double>
    }

    fun getCompanyData () : List<String>{

        val list : MutableList<String> = mutableListOf()

        when(productId.value){
            1 -> {
                for ( x in TubeLightListDataSource.tubeList)
                    if (watt.value?.equals(x.watt) == true)
                        list.add(x.company)
            }
            2 -> {
                for ( x in LedBulbDataSource.lb_blb)
                    if (watt.value?.equals(x.watt) == true)
                        list.add(x.company)
            }
            3 -> {
                for ( x in FilamentBulbDataSource.flm_blb)
                    if (watt.value?.equals(x.watt) == true)
                        list.add(x.company)
            }
            4 -> {
                for ( x in WiresDataSource.wireData)
                    if (watt.value?.equals(x.mm) == true)
                        list.add(x.company)
            }
            else -> list.add("error")
        }
        // removing duplicates and returning
        return list.toSet().toList().sorted() as MutableList<String>
    }

    fun getWireColorData() : List<String>{

        val list : MutableList<String> = mutableListOf()

        for ( x in WiresDataSource.wireData)
            if(_watt.value?.equals(x.mm) == true && _company.value?.equals(x.company) == true)
                list.add(x.colour)

        return list.toSet().toList().sorted() as MutableList<String>
    }
}