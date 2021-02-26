package com.mobile.programming.studentview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    var mRepository: StudentRepository = StudentRepository(application)
    var mAllStudents: LiveData<List<Student?>?>? = mRepository.getAllStudents()

    fun getAllStudents(): LiveData<List<Student?>?>?{
        return mAllStudents
    }

    fun insert(student: Student){
        mRepository.insert(student)
    }

    fun deleteStudent(student: Student){
        mRepository.deleteStudent(student)
    }

    fun deleteStudentById(nik: String){
        mRepository.deleteStudentById(nik)
    }

}