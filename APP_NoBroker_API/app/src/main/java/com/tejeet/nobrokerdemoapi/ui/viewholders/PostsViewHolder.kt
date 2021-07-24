package com.tejeet.nobrokerdemoapi.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tejeet.nobrokerdemoapi.dataModel.UserPosts
import com.tejeet.nobrokerdemoapi.ui.listners.PostsClickListner
import kotlinx.android.synthetic.main.property_item_layout.view.*

class PostsViewHolder(private val view : View, val itemClickListner: PostsClickListner) : RecyclerView.ViewHolder(view) {


    fun setData(postData : UserPosts){
        view.apply {
            tv_post_title.text = postData.title
            tv_post_description.text = postData.subTitle
            Glide.with(this)
                .load(postData.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_post_profile)

            iv_posts_card.setOnClickListener {
                itemClickListner.onItemClick(postData)
            }
        }
    }


}