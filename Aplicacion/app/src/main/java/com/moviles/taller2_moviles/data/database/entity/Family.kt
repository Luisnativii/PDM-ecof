package com.moviles.taller2_moviles.data.database.entity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Family",
)
data class Family(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "familyName")
    val familyName: String = "",

    @ColumnInfo(name = "location")
    val location: String = "", // Nombre del lugar

    @ColumnInfo(name = "geographicLocation")
    val geographicLocation: String = "", // Hacer que se guarde la ubicacion geograficas

    @ColumnInfo(name = "houseType")
    val houseType: String = "", //Cambiar para que sea un enum

    @ColumnInfo(name = "risk")
    val risk: String = "", //Cambiar para que sea un enum
)

