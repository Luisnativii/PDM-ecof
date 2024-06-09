package com.moviles.taller2_moviles.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moviles.taller2_moviles.data.database.entity.FamilyMember

@Dao
interface FamilyMemberDAO {
    //Insert Family Member
    @Insert(
        onConflict = OnConflictStrategy.ABORT
    )
    suspend fun insertFamilyMember(familyMember: FamilyMember)


//    Select all Family Members from a Family
    @Query("SELECT * FROM FamilyMember WHERE familyId = :id")
    suspend fun selectAllFamilyMembers(id: Int ): MutableList<FamilyMember>

    @Query("SELECT * FROM FamilyMember")
    suspend fun selectEveryFamilyMembers(): MutableList<FamilyMember>
}