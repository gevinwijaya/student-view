package com.mobile.programming.studentview.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class StudentRepository(application: Application) {

    var mStudentDao : StudentDao
    var mAllStudent : LiveData<List<Student?>?>?

    init {
        var db: StudentRoomDatabase? = StudentRoomDatabase.getDatabase(application)
        mStudentDao = db?.studentDao()!!
        mAllStudent = mStudentDao.getAllStudents()
    }

    fun getAllStudents() : LiveData<List<Student?>?>?{
        return mAllStudent
    }

    fun insert(student: Student){
        insertAsyncTask(mStudentDao).execute(student)
    }

    fun deleteStudent(student: Student){
        deleteStudentAsyncTask(mStudentDao).execute(student)
    }

    fun deleteStudentById(nik: String){
        deleteStudentById(nik)
    }

    class insertAsyncTask(var mAsyncTaskDao: StudentDao) : AsyncTask<Student, Void, Void>(){

        override fun doInBackground(vararg p0: Student?): Void? {
            mAsyncTaskDao.insert(p0[0])
            return null
        }
    }

    class deleteStudentAsyncTask(var mAsyncTaskDao: StudentDao) : AsyncTask<Student, Void, Void>(){

        override fun doInBackground(vararg p0: Student?): Void? {
            mAsyncTaskDao.deleteStudent(p0[0])
            return null
        }
    }

}