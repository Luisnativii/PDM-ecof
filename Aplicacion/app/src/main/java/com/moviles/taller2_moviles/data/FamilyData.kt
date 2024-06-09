package com.moviles.taller2_moviles.data

import androidx.compose.runtime.mutableStateListOf
import com.moviles.taller2_moviles.data.EnumDatas.HouseTypeEnum
import com.moviles.taller2_moviles.data.EnumDatas.RiskEnum
import com.moviles.taller2_moviles.data.EnumDatas.ScholarityEnum
import com.moviles.taller2_moviles.data.database.entity.Family
import com.moviles.taller2_moviles.data.database.entity.FamilyMember

var familyList = mutableListOf<Family>()


var familyMemberList = mutableStateListOf<FamilyMember>()