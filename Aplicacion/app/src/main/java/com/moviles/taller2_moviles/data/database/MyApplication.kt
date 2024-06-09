package com.moviles.taller2_moviles.data.database

import android.app.Application
import androidx.room.Room

class MyApplication: Application() {
    companion object{
        lateinit var database: FamilysDataBase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            FamilysDataBase::class.java,
            "FamilysDataBase"
        ).fallbackToDestructiveMigration().build()
    }
}