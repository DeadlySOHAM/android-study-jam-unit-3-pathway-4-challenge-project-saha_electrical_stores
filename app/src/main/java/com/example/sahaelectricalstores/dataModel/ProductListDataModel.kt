package com.example.sahaelectricalstores.dataModel

import androidx.annotation.DrawableRes

data class ProductListDataModel (
    val name : String ,
    @DrawableRes val pic : Int ,
    val id : Int
)