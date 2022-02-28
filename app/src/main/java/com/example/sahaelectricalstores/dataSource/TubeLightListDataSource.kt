package com.example.sahaelectricalstores.dataSource

import com.example.sahaelectricalstores.dataModel.LedTubelightDataModel

object TubeLightListDataSource {
    val tubeList : List<LedTubelightDataModel> = listOf(

        LedTubelightDataModel(20.0,"tSyska",39),
        LedTubelightDataModel(28.0,"tSyska",45),
        LedTubelightDataModel(40.0,"tSyska",50),

        LedTubelightDataModel(18.0,"Philyps",40),
        LedTubelightDataModel(28.0,"Philyps",51),
        LedTubelightDataModel(40.0,"Philyps",47),

        LedTubelightDataModel(18.0,"Havals",42),
        LedTubelightDataModel(20.0,"Havals",52),
        LedTubelightDataModel(40.0,"Havals",49),
    )
}