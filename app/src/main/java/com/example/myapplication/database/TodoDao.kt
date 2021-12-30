package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverter
import com.example.myapplication.entity.Todo
import java.util.*
@Dao
interface TodoDao {
    class DateConvertors {

        @TypeConverter
        fun toDate(dateLong: Long?): Date? = if (dateLong == null) null else Date(dateLong)

        @TypeConverter
        fun fromDate(date: Date?): Long? = date?.time
    }

    @Query("SELECT * FROM todoS")
    suspend fun getAll(): List<Todo>

    @Insert
    suspend fun add(todo: Todo)

    @Query("UPDATE todoS SET title=:title WHERE (id=:id)")
    suspend fun updateTitle(id: Int, title: String?)

    @Query("UPDATE todoS SET description=:description WHERE (id=:id)")
    suspend fun updateDescription(id: Int, description: String?)

    @Query("UPDATE todoS SET date=:date WHERE (id=:id)")
    suspend fun updateDate(id: Int, date: Date?)

    @Query("UPDATE todoS SET latitude=:latitude WHERE (id=:id)")
    suspend fun updateLatitude(id: Int, latitude: Double?)

    @Query("UPDATE todoS SET longitude=:longitude WHERE (id=:id)")
    suspend fun updateLongitude(id: Int, longitude: Double?)

    @Query("DELETE FROM todoS WHERE id=:todo")
    suspend fun deleteTodo(todo: Int)

    @Query("DELETE FROM todoS")
    suspend fun deleteAll()
}
