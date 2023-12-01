package com.example.sneakersapp.repository

import com.example.sneakersapp.ApiResponse
import com.example.sneakersapp.model.SneakerResponse

interface SneakerRepo {
    suspend fun getAllSneakers(): ApiResponse<SneakerResponse>
}