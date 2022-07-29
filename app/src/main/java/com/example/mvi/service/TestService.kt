package com.example.mvi.service

import retrofit2.http.GET

interface TestService {
    @GET("/")
    suspend fun test():HashMap<String,String>
}