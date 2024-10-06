package com.example.lab09

import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): PostModel
}