package com.example.appvelitjose.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvelitjose.models.Post
import com.example.appvelitjose.repositories.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val repository = PostRepository()

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val fetchedPosts = repository.getPosts()
                _posts.postValue(fetchedPosts)
            } catch (e: Exception) {
                e.printStackTrace() // Manejo de errores
            }
        }
    }
}