package com.mobile.programming.studentview.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mobile.programming.studentview.injection.DaggerStudentComponent
import javax.inject.Inject

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var mRepository: StudentRepository
    var mAllStudents: LiveData<List<Student?>?>? = null

    init {
        DaggerStudentComponent.create().inject(this)
        mAllStudents = mRepository.getAllStudents()
    }

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