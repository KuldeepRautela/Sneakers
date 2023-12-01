package com.example.sneakersapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_sneakers")
data class Result(
    val brand: String,
    val colorway: String,
    val gender: String,
    @PrimaryKey
    val id: String,
    @Embedded val media: Media,
    val name: String,
    val releaseDate: String,
    val retailPrice: Int,
)