package com.example.sahaelectricalstores.dataSource

import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.dataModel.ProductListDataModel

object ProductListDataSource {
    val productList : List<ProductListDataModel> = listOf(
        ProductListDataModel("LED Tubelight",R.drawable.led_tubelight,1),
        ProductListDataModel("LED Bulb",R.drawable.led_bulb,2),
        ProductListDataModel("Filament Bulb",R.drawable.filament_bulb,3),
        ProductListDataModel("Wires",R.drawable.wires,4),
    )
}