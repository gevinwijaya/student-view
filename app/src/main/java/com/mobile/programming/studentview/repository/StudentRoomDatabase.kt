package com.mobile.programming.studentview.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentRoomDatabase(): RoomDatabase() {
  abstract fun studentDao(): StudentDao?

  companion object {
    var INSTANCE: StudentRoomDatabase? = null

    @JvmStatic
    fun getDatabase(context: Context): StudentRoomDatabase?{
      if(INSTANCE == null){
        synchronized(StudentRoomDatabase::class){
          if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                StudentRoomDatabase::class.java, "student_database")
                .fallbackToDestructiveMigration()
                .addCallback(sRoomDatabaseCallback)
                .build();
          }
        }
      }
      return INSTANCE;
    }

     private val sRoomDatabaseCallback : Callback = object : Callback(){}

  }

}