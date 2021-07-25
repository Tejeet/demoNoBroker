package com.tejeet.nobrokerdemoapi.repository

import androidx.lifecycle.LiveData
import com.tejeet.guessmyageroommvvm.Model.UserDataBase
import com.tejeet.guessmyageroommvvm.Model.UserDataDao
import com.tejeet.guessmyageroommvvm.Model.UserPostsEntity
import com.tejeet.mvvmcoroutine.remote.APIService
import com.tejeet.mvvmcoroutine.remote.Resource
import com.tejeet.mvvmcoroutine.remote.ResponseHandler
import com.tejeet.mvvmcoroutine.remote.RetrofitGenerator
import com.tejeet.nobrokerdemoapi.dataModel.UserPosts

class PostsDataRepository(val userDataDao: UserDataDao) {

    private val CONTENT_TYPE = "application/json"

    val api : APIService = RetrofitGenerator.getInstance().create(APIService::class.java)
    val responseHandler = ResponseHandler()


    suspend fun getPosts() : Resource<List<UserPosts>>{

        val result : List<UserPosts> = api.getAllPosts(CONTENT_TYPE)

        userDataDao.deleteAllPostsDB()

        result.forEach {
            val title : String? = it.title
            val subTitle : String? = it.subTitle
            val Image : String? = it.image
            val data : UserPostsEntity = UserPostsEntity("${title}", "${subTitle}", "${Image}")
            userDataDao.setDBPosts(data)
        }

        return  responseHandler.handleSuccess(result)

    }

}