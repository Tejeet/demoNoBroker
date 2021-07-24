package com.tejeet.nobrokerdemoapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tejeet.mvvmcoroutine.remote.Resource
import com.tejeet.nobrokerdemoapi.dataModel.UserPosts
import com.tejeet.nobrokerdemoapi.repository.PostsDataRepository
import kotlinx.coroutines.Dispatchers

class PostsViewModel() : ViewModel() {

    val repo = PostsDataRepository()

    fun getPosts() : LiveData<List<UserPosts>>{

        return liveData(Dispatchers.IO){
            val result : Resource<List<UserPosts>> = repo.getPosts()

            emit(result.data!!)
        }
    }

}