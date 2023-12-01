package com.example.sneakersapp.repository

import android.util.Log
import com.example.sneakersapp.ApiResponse
import com.example.sneakersapp.db.SneakersDao
import com.example.sneakersapp.model.ApiService
import com.example.sneakersapp.model.Result
import com.example.sneakersapp.model.SneakerResponse
import okhttp3.Call
import okhttp3.Callback
import retrofit2.Response
import javax.inject.Inject

class SneakerRepoImpl @Inject constructor(private val apiService: ApiService,private val sneakersDao: SneakersDao) : SneakerRepo,SneakersCartRepo {
    override suspend fun getAllSneakers(): ApiResponse<SneakerResponse> =
        try {
            val data = apiService.sneakers()
            Log.e("Sneakers","${data.results}")
            ApiResponse.Success(data)
        } catch (exception: Exception) {
            Log.e("Exception", exception.localizedMessage)
            ApiResponse.Error(exception.localizedMessage)
        }

    override suspend fun getSneakersFromCart(): List<Result>? = sneakersDao.getFromCart()

    override suspend fun addToCart(result: Result) {
        sneakersDao.insert(result)
    }

    override suspend fun removeFromCart(id: String) {
        sneakersDao.removeItemFromCart(id)
    }

    override suspend fun getSneakerById(id: String): Result? {
        return sneakersDao.getSneakerById(id)
    }

}