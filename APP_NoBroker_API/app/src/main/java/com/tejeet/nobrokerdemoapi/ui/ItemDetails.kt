package com.tejeet.nobrokerdemoapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tejeet.nobrokerdemoapi.R
import com.tejeet.nobrokerdemoapi.constants.ConstantsData
import com.tejeet.nobrokerdemoapi.dataModel.UserPosts
import kotlinx.android.synthetic.main.activity_item_details.*

class ItemDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)



        val inData = intent.getSerializableExtra(ConstantsData.DATA_POST_TO_POST_DETAILS) as? UserPosts

        supportActionBar!!.title = "${inData?.title} Details"

        tv_post_title_details.text = inData?.title
        tv_post_description_details.text = inData?.subTitle

        Glide.with(this)
            .load(inData?.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(iv_post_profile_details)


    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.exit_first, R.anim.exit_second)
        finish()
    }


}