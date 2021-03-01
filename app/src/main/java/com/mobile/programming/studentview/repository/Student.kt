package com.mobile.programming.studentview.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
class Student (@PrimaryKey @ColumnInfo(name = "nik") var mNik: String,
    @ColumnInfo(name = "name") var mName: String,
    @ColumnInfo(name = "major") var mMajor: String,
    @ColumnInfo(name = "gender") var mGender: String,
    @ColumnInfo(name = "hobby") var mHobby: String) {
}