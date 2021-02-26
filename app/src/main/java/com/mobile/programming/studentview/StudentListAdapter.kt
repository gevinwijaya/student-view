package com.mobile.programming.studentview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.programming.studentview.databinding.RecyclerviewItemBinding

class StudentListAdapter(private val context: Context) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    var mStudents: List<Student?>? = null

    fun setStudents(students: List<Student?>?){
        mStudents = students
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemBinding = RecyclerviewItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return StudentViewHolder(itemBinding);
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        if(mStudents != null){
            var current: Student? = mStudents!![position]
            if (current != null) {
                holder.bind(current)
            }
        }
    }

    override fun getItemCount(): Int {
        if(mStudents != null)
            return mStudents!!.size
        else return 0
    }

    class StudentViewHolder(private val itemBinding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(student: Student){
            val desc: String = student.mMajor + " - " + student.mNik
            itemBinding.txtName.text = student.mName
            itemBinding.txtDesc.text = desc
        }
    }


}