package com.tejeet.guessmyageroommvvm.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class UserPostsEntity(
    @ColumnInfo(name = "image") var image : String,
    @ColumnInfo(name = "subTitle") var subTitle : String,
    @ColumnInfo(name = "title") var title : String ) {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int? = null

}