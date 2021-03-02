package com.mobile.programming.studentview.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mobile.programming.studentview.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class PhotoViewModel(private val photoRepository: PhotoRepository) : ViewModel() {
    fun getPhotos() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = photoRepository.getPhotos()))
        }catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}