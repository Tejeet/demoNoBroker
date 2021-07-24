package com.tejeet.mvvmcoroutine.remote
import com.tejeet.nobrokerdemoapi.constants.ConstantsData
import com.tejeet.nobrokerdemoapi.dataModel.UserPosts
import retrofit2.http.*

interface APIService {


    // Get All Available Properties

    @Headers("Accept: application/json")
    @GET(ConstantsData.POSTS_END_POINT)
    suspend fun getAllPosts(
        @Header("Content-Type") contentType: String
        ) : List<UserPosts>

}