package com.mobile.programming.studentview.repository.api

import com.mobile.programming.studentview.repository.Photo
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}