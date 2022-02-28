package com.example.sahaelectricalstores.dataSource

import com.example.sahaelectricalstores.dataModel.WireDataModel

object WiresDataSource {
    val wireData : List<WireDataModel> = listOf(
        WireDataModel(1.5,"Havals",11,"Red"),
        WireDataModel(4.0,"Havals",18,"Red"),
        WireDataModel(6.0,"Havals",21,"Red"),

        WireDataModel(4.0,"Havals",18,"Brown"),
        WireDataModel(6.0,"Havals",21,"Brown"),

        WireDataModel(1.5,"Havals",11,"Black"),
        WireDataModel(4.0,"Havals",18,"Black"),

        WireDataModel(1.0,"PolyCap",10,"Red"),
        WireDataModel(1.5,"PolyCap",12,"Red"),
        WireDataModel(6.0,"PolyCap",20,"Red"),

        WireDataModel(1.0,"PolyCap",10,"Yellow"),

        WireDataModel(1.5,"PolyCap",12,"Black"),
        WireDataModel(6.0,"PolyCap",20,"Black"),
    )
}