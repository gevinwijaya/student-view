package com.mobile.programming.studentview.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobile.programming.studentview.repository.api.ApiHelper

class PhotoViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PhotoViewModel::class.java)){
            return PhotoViewModel(PhotoRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}