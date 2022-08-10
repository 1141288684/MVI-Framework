package com.example.mvi.service

import com.example.mvi.viewmodel.User
import retrofit2.http.GET

interface TestService {
    @GET("/")
    suspend fun test():User
}