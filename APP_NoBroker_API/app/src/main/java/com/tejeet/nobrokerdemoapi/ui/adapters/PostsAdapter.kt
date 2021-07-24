package com.tejeet.nobrokerdemoapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tejeet.nobrokerdemoapi.R
import com.tejeet.nobrokerdemoapi.dataModel.UserPosts
import com.tejeet.nobrokerdemoapi.ui.listners.PostsClickListner
import com.tejeet.nobrokerdemoapi.ui.viewholders.PostsViewHolder

class PostsAdapter(private var postsList: List<UserPosts>, val itemClickListner: PostsClickListner) :
    RecyclerView.Adapter<PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.property_item_layout, parent, false)
        return PostsViewHolder(view, itemClickListner)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val postData = postsList[position]
        holder.setData(postData)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    fun updateData(postList: List<UserPosts> ){
        this.postsList = postList
        notifyDataSetChanged()
    }
}