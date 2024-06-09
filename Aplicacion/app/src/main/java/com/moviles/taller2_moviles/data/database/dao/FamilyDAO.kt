package com.moviles.taller2_moviles.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.moviles.taller2_moviles.data.database.entity.Family

@Dao
interface FamilyDAO {
    //Select All Families
    @Query("SELECT * FROM Family")
    suspend fun selectAllFamilies(): MutableList<Family>

    //Select Family by ID
    @Query("SELECT * FROM Family WHERE id = :id")
    suspend fun selectFamilyById(id: Int): Family

    //Insert Family
    //Insert Family
    @Insert(
        onConflict = OnConflictStrategy.ABORT
    )
    suspend fun insertFamily(family: Family) : Long

    //Update Family
    @Update(
        onConflict = OnConflictStrategy.ABORT
    )
    suspend fun updateFamily(family: Family)


    //Delete Family
    @Delete
    suspend fun deleteFamily(family: Family)





    @Query("DELETE FROM Family")
    suspend fun dropdata()

    @Query("DELETE FROM FamilyMember")
    suspend fun dropdataFamilyMember()
}