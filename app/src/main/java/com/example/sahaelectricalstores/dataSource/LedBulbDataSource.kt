package com.example.sahaelectricalstores.dataSource

import com.example.sahaelectricalstores.dataModel.LedBulbDataModel

object LedBulbDataSource {
    val lb_blb : List<LedBulbDataModel> = listOf(
        LedBulbDataModel(8.0,"tSyska",15),
        LedBulbDataModel(9.0,"tSyska",20),
        LedBulbDataModel(10.0,"tSyska",25),
        LedBulbDataModel(18.0,"tSyska",30),

        LedBulbDataModel(8.0,"Phylips",16),
        LedBulbDataModel(9.0,"Phylips",21),
        LedBulbDataModel(10.0,"Phylips",26),
        LedBulbDataModel(18.0,"Phylips",31),

        LedBulbDataModel(8.0,"Havals",17),
        LedBulbDataModel(9.0,"Havals",22),
        LedBulbDataModel(10.0,"Havals",27),
        LedBulbDataModel(18.0,"Havals",32),
    )
}