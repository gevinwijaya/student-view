package com.mobile.programming.studentview.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mobile.programming.studentview.databinding.RecyclerviewItemBinding
import com.mobile.programming.studentview.repository.Student
import com.mobile.programming.studentview.view.StudentDetailActivity
import com.mobile.programming.studentview.view.StudentDetailDialog

class StudentListAdapter(val context: Context) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    var mStudents: List<Student?>? = null

    private lateinit var listener: ItemListener

    interface ItemListener {
        fun onItemClicked(student: Student?, position: Int)
    }

    fun setListener(listener: ItemListener) {
        this.listener = listener;
    }

    fun setStudents(students: List<Student?>?){
        mStudents = students
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemBinding = RecyclerviewItemBinding.inflate(mInflater, parent, false)
        return StudentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        if(mStudents != null){
            var current: Student? = mStudents!![position]
            if (current != null) {
                holder.bind(current)
            }
        }
        holder.itemBinding.btnDelete.setOnClickListener{
            listener.onItemClicked(mStudents?.get(position),position)
            notifyDataSetChanged()
        }
        holder.itemBinding.btnShare.setOnClickListener{
            val student = mStudents?.get(position)
            val text = "NIM: " + student!!.mNik + System.lineSeparator() +
                    "Nama: " + student.mName + System.lineSeparator() +
                    "Jurusan: " + student.mMajor + System.lineSeparator() +
                    "Gender: " + student.mGender + System.lineSeparator() +
                    "Hobby: " + student.mHobby
            ShareCompat.IntentBuilder
                .from(context as Activity)
                .setType("text/plain")
                .setText(text)
                .startChooser()
        }
        holder.itemView.setOnClickListener{
            val student = mStudents?.get(position)
//            val replyIntent = Intent(context, StudentDetailActivity::class.java)
//            replyIntent.putExtra("name", student!!.mName)
//            replyIntent.putExtra("nik", student.mNik)
//            replyIntent.putExtra("major",student.mMajor)
//            replyIntent.putExtra("gender",student.mGender)
//            replyIntent.putExtra("hobby",student.mHobby);
//            context.startActivity(replyIntent)

            val activity = context as AppCompatActivity

            if (student != null) {
                StudentDetailDialog.newInstance(student.mName,
                    student.mNik,
                    student.mMajor,
                    student.mGender,
                    student.mHobby).show(activity.supportFragmentManager, StudentDetailDialog.TAG)
            }
        }
    }


    override fun getItemCount(): Int {
        if(mStudents != null)
            return mStudents!!.size
        else return 0
    }

    class StudentViewHolder(
        val itemBinding: RecyclerviewItemBinding
    ) :
        RecyclerView.ViewHolder(
            itemBinding.root
        ) {

        fun bind(student: Student){
            val desc: String = student.mMajor + " - " + student.mNik
            itemBinding.txtName.text = student.mName
            itemBinding.txtDesc.text = desc
        }
    }


}