package com.tejeet.nobrokerdemoapi.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tejeet.nobrokerdemoapi.R
import com.tejeet.nobrokerdemoapi.constants.ConstantsData
import com.tejeet.nobrokerdemoapi.dataModel.UserPosts
import com.tejeet.nobrokerdemoapi.ui.adapters.PostsAdapter
import com.tejeet.nobrokerdemoapi.ui.listners.PostsClickListner
import com.tejeet.nobrokerdemoapi.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PostsClickListner {

    private lateinit var postsAdapter: PostsAdapter
    private var postListData: List<UserPosts> = listOf()

    private val TAG = "tag"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerAdatapter()

        shimmerFrameLayout.startShimmerAnimation()
        shimmerFrameLayout.visibility = View.VISIBLE
        rcvPosts.visibility = View.GONE

        val myViewModel: PostsViewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)

        myViewModel.getPosts().observe(this, {

            val resp: List<UserPosts> = it!!

            Log.d(TAG, "Response is ${resp.size}")
            postListData = resp;
            postsAdapter.updateData(resp)
            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE
            rcvPosts.visibility = View.VISIBLE

        })

        et_posts_query.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG, "afterTextChanged: ${s}")
                filter(s.toString());
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })


    }

    fun filter(text: String) {
        Log.d(TAG, "Orifinal List is ${postListData}")
        val filteredList: ArrayList<UserPosts> = ArrayList()
        for (item in postListData) {
            if (item.title!!.toLowerCase().contains(text.toLowerCase()) || item.subTitle!!.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        postsAdapter.updateData(filteredList)
    }

    override fun onBackPressed() {
        showCloseAppDialogue()
    }

    override fun onStart() {
        super.onStart()

    }


    override fun onStop() {
        super.onStop()
    }


    private fun showCloseAppDialogue() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Close App ?")
        builder.setMessage("Do You Wants to Close the App")

        builder.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, id ->
            finish()
        })

        builder.setNegativeButton("NO", DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()

        })

        builder.setCancelable(true)
        builder.show()
    }

    private fun setRecyclerAdatapter() {
        postsAdapter = PostsAdapter(postListData, this)
        val LinearLayoutManager = LinearLayoutManager(this)
        rcvPosts.layoutManager = LinearLayoutManager
        rcvPosts.adapter = postsAdapter
    }

    override fun onItemClick(data: UserPosts) {

        Log.d(TAG, "Post Item Clicked is ${data.title}")
        gotoPostdetails(data)

    }

    private fun gotoPostdetails(postData: UserPosts) {
        intent = Intent(this, ItemDetails::class.java);
        intent.putExtra(ConstantsData.DATA_POST_TO_POST_DETAILS, postData)
        startActivity(intent);
        overridePendingTransition(R.anim.enter_first, R.anim.enter_second);
        finish();
    }
}