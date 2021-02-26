package com.mobile.programming.studentview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.programming.studentview.databinding.RecyclerviewItemBinding

class StudentListAdapter(val context: Context) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    var mStudents: List<Student?>? = null

    fun setStudents(students: List<Student?>?){
        mStudents = students
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemBinding = RecyclerviewItemBinding.inflate(mInflater, parent, false)
        return StudentViewHolder(itemBinding, mStudents);
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

    class StudentViewHolder(
        private val itemBinding: RecyclerviewItemBinding,
        private val mStudents: List<Student?>?
    ) :
        RecyclerView.ViewHolder(
            itemBinding.root
        ) {

        fun bind(student: Student){
            val desc: String = student.mMajor + " - " + student.mNik
            itemBinding.txtName.text = student.mName
            itemBinding.txtDesc.text = desc

            itemBinding.btnDelete.setOnClickListener{
                val replyIntent = Intent()
                replyIntent.putExtra("nik", mStudents!!.get(adapterPosition)!!.mNik)

            }
            itemBinding.btnShare.setOnClickListener{

            }
        }
    }


}