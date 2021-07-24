package com.tejeet.nobrokerdemoapi.repository

import androidx.lifecycle.LiveData
import com.tejeet.mvvmcoroutine.remote.APIService
import com.tejeet.mvvmcoroutine.remote.Resource
import com.tejeet.mvvmcoroutine.remote.ResponseHandler
import com.tejeet.mvvmcoroutine.remote.RetrofitGenerator
import com.tejeet.nobrokerdemoapi.dataModel.UserPosts

class PostsDataRepository {

    private val CONTENT_TYPE = "application/json"

    val api : APIService = RetrofitGenerator.getInstance().create(APIService::class.java)
    val responseHandler = ResponseHandler()

    suspend fun getPosts() : Resource<List<UserPosts>>{

        val result : List<UserPosts> = api.getAllPosts(CONTENT_TYPE)

        return  responseHandler.handleSuccess(result)

    }

}