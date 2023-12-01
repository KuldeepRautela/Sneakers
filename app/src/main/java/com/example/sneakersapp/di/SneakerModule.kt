package com.example.sneakersapp.di

import android.content.Context
import androidx.room.Room
import com.example.sneakersapp.MockInterceptor
import com.example.sneakersapp.db.SneakerDatabase
import com.example.sneakersapp.db.SneakersDao
import com.example.sneakersapp.model.ApiService
import com.example.sneakersapp.repository.SneakerRepo
import com.example.sneakersapp.repository.SneakerRepoImpl
import com.example.sneakersapp.repository.SneakersCartRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SneakerModule {
    @Provides
    @Singleton
    fun getRetrofit(client:OkHttpClient): Retrofit = Retrofit.Builder().baseUrl("https://sneakers.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun getOkHttpClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder().addInterceptor(MockInterceptor(context)).build()

    @Provides
    @Singleton
    fun getSneakerRepo(apiService: ApiService,sneakersDao: SneakersDao) : SneakerRepo = SneakerRepoImpl(apiService,sneakersDao)

    @Provides
    @Singleton
    fun getCartRepo(apiService: ApiService,sneakersDao: SneakersDao) : SneakersCartRepo = SneakerRepoImpl(apiService,sneakersDao)

    @Provides
    @Singleton
    fun getDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        SneakerDatabase::class.java,
        "sneaker_db"
    ).build()

    @Provides
    @Singleton
    fun getSneakerDao(db : SneakerDatabase) = db.getSneakerDao()
}