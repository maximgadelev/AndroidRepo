package com.example.myapplication

import android.app.Activity
import androidx.room.Room
import com.example.myapplication.database.DataBase

class DataBaseCreator {

    fun createDBInstance(activity: Activity): DataBase {
        return Room.databaseBuilder(activity, DataBase::class.java, "dao")
            .build()
    }

    fun initDB(activity: Activity): DataBase {
        val db = createDBInstance(activity)
        return db
    }
}
