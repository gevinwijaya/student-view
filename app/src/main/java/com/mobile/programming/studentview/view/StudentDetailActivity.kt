package com.mobile.programming.studentview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.programming.studentview.R
import com.mobile.programming.studentview.databinding.ActivityStudentDetailBinding

class StudentDetailActivity : AppCompatActivity() {

  private lateinit var detailBinding: ActivityStudentDetailBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    detailBinding = ActivityStudentDetailBinding.inflate(layoutInflater)
    setContentView(R.layout.activity_student_detail)
    val view = detailBinding.root
    setContentView(view)
    setSupportActionBar(detailBinding.toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    val intent = intent
    val nik = intent.getStringExtra("nik")
    val name = intent.getStringExtra("name")
    val gender = intent.getStringExtra("gender")
    val major = intent.getStringExtra("major")
    val hobby = intent.getStringExtra("hobby")

    detailBinding.txtDetailName.text = ": " + name
    detailBinding.txtDetailNik.text = ": " + nik
    detailBinding.txtDetailGender.text = ": " + gender
    detailBinding.txtDetailMajor.text = ": " + major
    detailBinding.txtDetailHobby.text = ": " + hobby
  }
}