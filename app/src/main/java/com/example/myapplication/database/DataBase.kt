package com.example.myapplication.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.entity.Todo

@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(TodoDao.DateConvertors::class)
abstract class DataBase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
