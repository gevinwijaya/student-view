package com.mobile.programming.studentview

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobile.programming.studentview.databinding.ActivityNewStudentBinding

class NewStudentActivity : AppCompatActivity() {

    private lateinit var newStudentBinding: ActivityNewStudentBinding
    private lateinit var majorSpinner: Spinner
    private lateinit var hobbySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newStudentBinding = ActivityNewStudentBinding.inflate(layoutInflater)
        val view = newStudentBinding.root
        setContentView(view)
        setSupportActionBar(newStudentBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        majorSpinner = newStudentBinding.spinnerMajor
        val majorAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.major_array,
            android.R.layout.simple_spinner_item
        )
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        majorSpinner.adapter = majorAdapter

        hobbySpinner = newStudentBinding.spinnerHobby
        val hobbyAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.hobi_array,
            android.R.layout.simple_spinner_item
        )
        hobbyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        hobbySpinner.adapter = hobbyAdapter


        newStudentBinding.btnSubmit.setOnClickListener{
            val replyIntent = Intent()
            if(!validateInput()){
                setResult(RESULT_CANCELED, replyIntent)
            }
            else{
                var name: String = newStudentBinding.editTextName.text.toString()
                var nik: String = newStudentBinding.editTextNik.text.toString()
                var major: String = newStudentBinding.spinnerMajor.selectedItem.toString()
                var gender_id: Int = newStudentBinding.radioGroup.checkedRadioButtonId
                var gender: String
                if(newStudentBinding.radioLaki.id == gender_id)
                    gender = newStudentBinding.radioLaki.text.toString()
                else
                    gender = newStudentBinding.radioPerempuan.text.toString()
                var hobby: String = newStudentBinding.spinnerHobby.selectedItem.toString()

                replyIntent.putExtra("name",name)
                replyIntent.putExtra("nik",nik)
                replyIntent.putExtra("major",major)
                replyIntent.putExtra("gender",gender)
                replyIntent.putExtra("hobby",hobby);
                displayToast("Added new student")
                setResult(RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    private fun displayToast(message: String?) {
        Toast.makeText(
            applicationContext, message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun isNumeric(string: String) : Boolean{
//        try {
//            val num = parseDouble(string)
//        } catch (e: NumberFormatException){
//            return false
//        }
//        return true
        for (char in string) {
            if(!Character.isDigit(char)){
                return false;
            }
        }
        return true;
    }

    private fun validateInput():Boolean{
        when{
            TextUtils.isEmpty(newStudentBinding.editTextName.text) -> {
                displayToast("Name is empty")
                return false
            }
            newStudentBinding.editTextName.text.toString().length < 3 -> {
                displayToast("Name must be at least 3 characters long")
                return false
            }
            TextUtils.isEmpty(newStudentBinding.editTextNik.text) -> {
                displayToast("NIM is empty")
                return false
            }
            newStudentBinding.editTextNik.text.toString().length != 10 -> {
                displayToast("NIM must be 10 characters long")
                return false
            }
            isNumeric(newStudentBinding.editTextNik.toString()) -> {
                displayToast("NIM must only contain numbers")
                return false
            }
            majorSpinner.selectedItem == null -> {
                displayToast("Please select a major")
                return false
            }
            newStudentBinding.radioGroup.checkedRadioButtonId == -1 -> {
                displayToast("Please specify your gender")
                return false
            }
            hobbySpinner.selectedItem == null -> {
                displayToast("Please select a hobby")
                return false
            }
            else -> return true
        }
    }
}