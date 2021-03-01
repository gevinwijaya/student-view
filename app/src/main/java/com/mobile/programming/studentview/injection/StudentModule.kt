package com.mobile.programming.studentview.injection

import com.mobile.programming.studentview.repository.StudentRepository
import dagger.Module
import dagger.Provides

@Module
class StudentModule{

    private val application = MyApp.getApplication()

    @Provides
    fun getRepository(): StudentRepository {
        return StudentRepository(application)
    }

}