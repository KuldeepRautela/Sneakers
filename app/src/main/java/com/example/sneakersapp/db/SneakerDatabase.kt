package com.example.sneakersapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sneakersapp.model.Result

@Database(entities = [Result::class], version = 1)
abstract class SneakerDatabase : RoomDatabase() {
    abstract fun getSneakerDao(): SneakersDao
}