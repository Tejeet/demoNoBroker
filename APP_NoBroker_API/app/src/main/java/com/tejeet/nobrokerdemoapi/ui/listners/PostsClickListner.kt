package com.tejeet.nobrokerdemoapi.ui.listners

import com.tejeet.nobrokerdemoapi.dataModel.UserPosts

interface PostsClickListner {

    fun onItemClick(data: UserPosts)
}