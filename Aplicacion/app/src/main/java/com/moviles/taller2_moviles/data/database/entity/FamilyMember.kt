package com.moviles.taller2_moviles.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "FamilyMember",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Family::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("familyId"),
            onDelete = ForeignKey.CASCADE,
        )
    )
)
data class FamilyMember(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int  = 0,

    @ColumnInfo(name = "DUI")
    val DUI: String = "",

    @ColumnInfo(name = "fullName")
    val fullName: String = "",

    @ColumnInfo(name = "dateBirth")
    val dateBirth: String = "", // Poner un calendario?

    @ColumnInfo(name = "canReadWrite")
    val canReadWrite: Boolean = false,

    @ColumnInfo(name = "scholarity")
    val scholarity: String = "", // Cambiar para que sea un enum


    //FK Family -> FamilyMember
    @ColumnInfo(name = "familyId", index = true)
    var familyId : Int = 0,
)
