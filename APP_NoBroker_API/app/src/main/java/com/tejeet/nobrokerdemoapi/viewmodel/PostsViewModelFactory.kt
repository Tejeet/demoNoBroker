package com.tejeet.nobrokerdemoapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tejeet.nobrokerdemoapi.repository.PostsDataRepository

class PostsViewModelFactory(val repository: PostsDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return PostsViewModel(repository) as T
    }
}