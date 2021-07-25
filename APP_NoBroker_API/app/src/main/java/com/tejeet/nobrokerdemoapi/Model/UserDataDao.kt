package com.tejeet.guessmyageroommvvm.Model

import android.service.autofill.UserData
import androidx.room.*

@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun setDBPosts(userPostsEntity : UserPostsEntity)

    @Query("SELECT * FROM posts")
    fun getDBPosts(): List<UserPostsEntity>

    @Query("DELETE FROM posts")
    fun deleteAllPostsDB()


}