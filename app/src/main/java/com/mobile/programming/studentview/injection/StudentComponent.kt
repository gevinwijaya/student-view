package com.mobile.programming.studentview.injection

import com.mobile.programming.studentview.repository.StudentViewModel
import dagger.Component

@Component(modules = [StudentModule::class])
interface StudentComponent {
    fun inject(s: StudentViewModel)
}