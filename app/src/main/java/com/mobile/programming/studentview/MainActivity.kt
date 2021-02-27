package com.mobile.programming.studentview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.programming.studentview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), StudentListAdapter.ItemListener {

  lateinit var mStudentViewModel: StudentViewModel
  private lateinit var mainBinding: ActivityMainBinding

  val NEW_STUDENT_ACTIVITY_REQUEST_CODE: Int = 1;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mainBinding = ActivityMainBinding.inflate(layoutInflater)
    val view = mainBinding.root
    setContentView(view)
    setSupportActionBar(mainBinding.toolbar)

    val recyclerView: RecyclerView = mainBinding.recyclerview
    val adapter = StudentListAdapter(this)
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(this)
    adapter.setListener(this)

    mStudentViewModel = ViewModelProviders.of(this).get(StudentViewModel(application)::class.java)
    mStudentViewModel.getAllStudents()?.observe(this,
      Observer { students ->
        adapter.setStudents(students)
      })

    mainBinding.fab.setOnClickListener {
      val intent = Intent(this, NewStudentActivity::class.java)
      startActivityForResult(intent, NEW_STUDENT_ACTIVITY_REQUEST_CODE)
    }


  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == NEW_STUDENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
      val student = Student(
        data!!.getStringExtra("nik"),
        data.getStringExtra("name"),
        data.getStringExtra("major"),
        data.getStringExtra("gender"),
        data.getStringExtra("hobby"))
      // Save the data
      mStudentViewModel.insert(student)
    }
//    else if(requestCode == DELETE_STUDENT_REQUEST_CODE && resultCode == RESULT_OK){
//      mStudentViewModel.deleteStudentById(data!!.getStringExtra("nik"))
//    }
  }

  override fun onItemClicked(student: Student?, position: Int) {
    if (student != null) {
      mStudentViewModel.deleteStudent(student)
    }
  }
}