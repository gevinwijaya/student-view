package com.mobile.programming.studentview

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(student: Student?)

  @Delete
  fun deleteStudent(student: Student?)

  @Query("SELECT * from student_table LIMIT 1")
  fun getAnyStudent():Array<Student?>?

  @Query("SELECT * from student_table")
  fun getAllStudents(): LiveData<List<Student?>?>?

}