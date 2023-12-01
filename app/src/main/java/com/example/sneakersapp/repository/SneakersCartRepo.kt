package com.example.sneakersapp.repository

import com.example.sneakersapp.model.Result

interface SneakersCartRepo {
    suspend fun getSneakersFromCart(): List<Result>?
    suspend fun addToCart(result: Result)
    suspend fun removeFromCart(id:String)
    suspend fun getSneakerById(id:String):Result?
}