package com.tejeet.nobrokerdemoapi.ui

import android.app.Application
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.tejeet.guessmyageroommvvm.Model.UserDataBase
import com.tejeet.nobrokerdemoapi.repository.PostsDataRepository

class UserApplication : Application() {

    val usersDao by lazy {

        val myRoomDb : UserDataBase = UserDataBase.getDataBase(this)
        myRoomDb.getUserDataDao()
    }

    val repository : PostsDataRepository by lazy {
        PostsDataRepository(usersDao)
    }




}