package com.example.appvelitjose.repositories

import com.example.appvelitjose.models.Post
import com.example.appvelitjose.network.ApiService

class PostRepository {

    private val apiService = ApiService.create()

    suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }
}