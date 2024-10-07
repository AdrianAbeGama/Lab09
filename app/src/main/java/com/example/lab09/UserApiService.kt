package com.example.lab09.api

import com.example.lab09.model.UserModel
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserModel>
}
