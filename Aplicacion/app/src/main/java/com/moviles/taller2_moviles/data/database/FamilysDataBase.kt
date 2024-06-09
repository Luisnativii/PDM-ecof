package com.moviles.taller2_moviles.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moviles.taller2_moviles.data.database.dao.FamilyDAO
import com.moviles.taller2_moviles.data.database.dao.FamilyMemberDAO
import com.moviles.taller2_moviles.data.database.entity.Family
import com.moviles.taller2_moviles.data.database.entity.FamilyMember

@Database(
    entities = [
        Family::class,
        FamilyMember::class],
    version = 1
)
abstract class FamilysDataBase: RoomDatabase() {
    abstract fun familyDAO(): FamilyDAO
    abstract fun familyMemberDAO(): FamilyMemberDAO
}