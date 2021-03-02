package com.mobile.programming.studentview.repository

import com.mobile.programming.studentview.repository.api.ApiHelper

class PhotoRepository(private val apiHelper: ApiHelper) {
    suspend fun getPhotos() = apiHelper.getPhotos()
}