package com.mobile.programming.studentview.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.mobile.programming.studentview.R
import kotlinx.android.synthetic.main.fragment_student_detail.view.*

class StudentDetailDialog: DialogFragment() {

    interface ItemListener {
        fun displayToast(name: String)
    }

    lateinit var listener: ItemListener
    lateinit var toolbar: Toolbar

    companion object {

        const val TAG = "StudentDetailDialog"

        private const val KEY_NIK = "KEY_NIK"
        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_MAJOR = "KEY_MAJOR"
        private const val KEY_GENDER = "KEY_GENDER"
        private const val KEY_HOBBY = "KEY_HOBBY"

        fun newInstance(name: String, nik: String, major: String, gender: String, hobby: String): StudentDetailDialog {
            val args = Bundle()
            args.putString(KEY_NIK, nik)
            args.putString(KEY_NAME, name)
            args.putString(KEY_MAJOR, major)
            args.putString(KEY_GENDER, gender)
            args.putString(KEY_HOBBY, hobby)

            val fragment = StudentDetailDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(
            DialogFragment.STYLE_NORMAL,
            android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen
        );
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val parentView = inflater.inflate(R.layout.fragment_student_detail, container, false)
        return parentView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ItemListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    private fun setupView(view: View) {
        view.toolbar.title = "Nama"
        view.txtDetailNik.text = arguments?.getString(KEY_NIK)
        view.txtDetailName.text = arguments?.getString(KEY_NAME)
        view.txtDetailGender.text = arguments?.getString(KEY_GENDER)
        view.txtDetailMajor.text = arguments?.getString(KEY_MAJOR)
        view.txtDetailHobby.text = arguments?.getString(KEY_HOBBY)
    }

    private fun setupClickListeners(view: View) {
        view.btnBack.setOnClickListener{
            dismiss()
        }
        view.btnToast.setOnClickListener{
            listener.displayToast(view.txtDetailName.text.toString())
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}