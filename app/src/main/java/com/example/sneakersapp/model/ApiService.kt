package com.example.sneakersapp.model

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("mock_response")
    suspend fun sneakers() : SneakerResponse
}