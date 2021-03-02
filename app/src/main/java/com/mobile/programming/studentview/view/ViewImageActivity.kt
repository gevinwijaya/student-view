package com.mobile.programming.studentview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mobile.programming.studentview.R
import com.mobile.programming.studentview.adapter.PhotoAdapter
import com.mobile.programming.studentview.databinding.ActivityViewImageBinding
import com.mobile.programming.studentview.repository.Photo
import com.mobile.programming.studentview.repository.PhotoViewModel
import com.mobile.programming.studentview.repository.PhotoViewModelFactory
import com.mobile.programming.studentview.repository.api.ApiHelper
import com.mobile.programming.studentview.repository.api.RetrofitBuilder
import com.mobile.programming.studentview.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_image.*

class ViewImageActivity : AppCompatActivity() {

  private lateinit var viewImageBinding: ActivityViewImageBinding
  private lateinit var viewModel: PhotoViewModel
  private lateinit var adapter: PhotoAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewImageBinding = ActivityViewImageBinding.inflate(layoutInflater)
    val view = viewImageBinding.root
    setContentView(view)
    setSupportActionBar(viewImageBinding.toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    setupViewModel()
    setupUI()
    setupObservers()

  }

  private fun setupObservers() {
    viewModel.getPhotos().observe(this, Observer {
      it?.let { resource ->
        when (resource.status){
          Status.SUCCESS -> {
            gridview.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            resource.data?.let { photos->retrieveList(photos) }
          }
          Status.ERROR -> {
            gridview.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            Toast.makeText(this, it.message, Toast.LENGTH_LONG)
          }
          Status.LOADING -> {
            progressBar.visibility = View.VISIBLE
            gridview.visibility = View.GONE
          }
        }
      }
    })
  }

  private fun retrieveList(photos: List<Photo>){
    adapter.apply {
      addPhotos(photos)
      notifyDataSetChanged()
    }
  }

  private fun setupUI() {
    adapter = PhotoAdapter(arrayListOf(),this)
    gridview.adapter = adapter
  }

  private fun setupViewModel() {
    viewModel = ViewModelProviders.of(
      this,
      PhotoViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
    ).get(PhotoViewModel::class.java)
  }
}