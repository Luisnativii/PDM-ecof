package com.moviles.taller2_moviles.data.api

import androidx.compose.ui.unit.Constraints
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.moviles.taller2_moviles.utils.Constants

data class FamilyApi(
    //@SerializedName(value = "_id")
    //val id_: String = "",

    @SerializedName(value = Constants.FAMILY_ID)
    val id: Int? = 0,

    @SerializedName(value = Constants.FAMILY_NAME)
    val familyName: String? = "",

    @SerializedName(value = Constants.LOCATION)
    val location: String? = "", // Nombre del lugar

    @SerializedName(value = Constants.GEOGRAPHIC_LOCATION)
    val geographicLocation: String? = "", // Hacer que se guarde la ubicacion geograficas

    @SerializedName(value = Constants.HOUSE_TYPE)
    val houseType: String? = "", //Cambiar para que sea un enum

    @SerializedName(value = Constants.RISK)
    val risk: String? = "", //Cambiar para que sea un enum

    @SerializedName(value = Constants.FAMILY_MEMBERS)
    val FamilyMembers: List<MembersApi> = arrayListOf(),

    )

data class MembersApi(


        @SerializedName(value = Constants.DUI)
        val DUI: String? = "",

        @SerializedName(value = Constants.FULL_NAME)
        val fullName: String? = "",

        @SerializedName(value = Constants.DATE_BIRTH)
        val dateBirth: String? = "", // Poner un calendario?

        @SerializedName(value = Constants.CAN_READ_WRITE)
        val canReadWrite: Boolean? = false,

        @SerializedName(value = Constants.SCHOLARITY)
        val scholarity: String? = "", // Cambiar para que sea un enum
)
