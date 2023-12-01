package com.example.sneakersapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakersapp.model.Result


@Dao
interface SneakersDao {
    @Query("Select * from cart_sneakers")
    suspend fun getFromCart(): List<Result>?

    @Query("Select * from cart_sneakers WHERE id = :id")
    suspend fun getSneakerById(id: String): Result?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sneaker: Result)

    @Query("DELETE FROM cart_sneakers WHERE id = :id")
    fun removeItemFromCart(id: String)
}
