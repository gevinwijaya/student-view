package com.mobile.programming.studentview

import android.content.Context
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentRoomDatabase(): RoomDatabase() {
  abstract fun studentDao(): StudentDao?

  companion object {
    var INSTANCE: StudentRoomDatabase? = null

    @JvmStatic
    fun getDatabase(context: Context):StudentRoomDatabase?{
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

//    val sRoomDatabaseCallback : RoomDatabase.Callback = object : Callback(){
//      override fun onOpen(db: SupportSQLiteDatabase) {
//        super.onOpen(db)
//      }
//    }

//    class PopulateDbAsync(db: StudentRoomDatabase?) : AsyncTask<Void, Void, Void>{
//
//      var mDao: StudentDao? = null
//
//      init {
//          mDao = db?.studentDao()
//      }
//
//      override fun doInBackground(vararg p0: Void?): Void {
//        if(mDao?.getAnyStudent()?.size? < 1){
//
//        }
//      }
//
//    }
  }

}