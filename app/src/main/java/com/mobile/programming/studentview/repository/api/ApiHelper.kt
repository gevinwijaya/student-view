package com.mobile.programming.studentview.repository.api

class ApiHelper (private val apiService: ApiService){
    suspend fun getPhotos() = apiService.getPhotos()
}