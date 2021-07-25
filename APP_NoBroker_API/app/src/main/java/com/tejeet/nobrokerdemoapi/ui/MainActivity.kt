package com.tejeet.nobrokerdemoapi.ui

import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tejeet.guessmyageroommvvm.Model.UserPostsEntity
import com.tejeet.nobrokerdemoapi.R
import com.tejeet.nobrokerdemoapi.constants.ConstantsData
import com.tejeet.nobrokerdemoapi.dataModel.UserPosts
import com.tejeet.nobrokerdemoapi.repository.PostsDataRepository
import com.tejeet.nobrokerdemoapi.ui.adapters.PostsAdapter
import com.tejeet.nobrokerdemoapi.ui.listners.PostsClickListner
import com.tejeet.nobrokerdemoapi.viewmodel.PostsViewModel
import com.tejeet.nobrokerdemoapi.viewmodel.PostsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), PostsClickListner {

    private lateinit var postsAdapter: PostsAdapter
    private var postListData: List<UserPosts> = listOf()

    private val TAG = "tag"

    private lateinit var viewModel : PostsViewModel

    lateinit var dbData : UserPostsEntity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerAdatapter()

        shimmerFrameLayout.startShimmerAnimation()
        shimmerFrameLayout.visibility = View.VISIBLE
        rcvPosts.visibility = View.GONE


        val appObj  = application as UserApplication
        val reposotory : PostsDataRepository = appObj.repository

        val viewModelFactory : PostsViewModelFactory = PostsViewModelFactory(reposotory)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)


        if(isNetworkConnected()){

            viewModel.getPosts().observe(this, {

                val resp: List<UserPosts> = it!!

                Log.d(TAG, "Response is ${resp.size}")
                postListData = resp;
                postsAdapter.updateData(resp)
                shimmerFrameLayout.stopShimmerAnimation()
                shimmerFrameLayout.visibility = View.GONE
                rcvPosts.visibility = View.VISIBLE

            })
        }
        else{

            Toast.makeText(this, " No Internet loading Cached data ", Toast.LENGTH_SHORT)
                .show()

            CoroutineScope(Dispatchers.IO).launch {
                val resp: List<UserPostsEntity> = appObj.usersDao.getDBPosts()

                var postDataDB  = ArrayList<UserPosts>()


                resp.forEach {
                    val data = UserPosts(it.title, it.subTitle, it.image)
                    postDataDB.add(data)
                }

                postsAdapter.updateData(postDataDB)

            }

            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE
            rcvPosts.visibility = View.VISIBLE



        }



        sv_post_query.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filter(it) }
                return true
            }
        })


    }

    fun filter(text: String) {
        Log.d(TAG, "Original List is ${postListData}")
        val filteredList: ArrayList<UserPosts> = ArrayList()
        for (item in postListData) {
            if (item.title!!.toLowerCase().contains(text.toLowerCase())) {
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
        gotoPostdetails(data)  // Pass Clicked Item to Next Activity

    }

    private fun gotoPostdetails(postData: UserPosts) {

        intent = Intent(this, ItemDetails::class.java);
        intent.putExtra(ConstantsData.DATA_POST_TO_POST_DETAILS, postData)
        startActivity(intent);
        overridePendingTransition(R.anim.enter_first, R.anim.enter_second);
        finish();
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}