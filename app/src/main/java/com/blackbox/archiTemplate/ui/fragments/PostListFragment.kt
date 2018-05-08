package com.blackbox.archiTemplate.ui.fragments

import android.annotation.TargetApi
import android.app.ActivityOptions
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blackbox.archiTemplate.R
import com.blackbox.archiTemplate.data.entity.Posts
import com.blackbox.archiTemplate.ui.activities.DetailsActivity
import com.blackbox.archiTemplate.ui.items.PostItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import kotlinx.android.synthetic.main.item_simple.view.*
import javax.inject.Inject

/**
 * Created by umair on 08/01/2018.
 */
class PostListFragment : BaseFragment() {

    var TAG = PostListFragment::class.java.simpleName

    private lateinit var postsViewModel: PostsViewModel

    private lateinit var mAdapter: FastItemAdapter<PostItem>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {

        //Return new instance of this Fragment
        fun newInstance(): PostListFragment {
            return PostListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Add custom ViewModel factory
        postsViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize RecyclerView with click listeners
        initRecycleView()

        //Add pull to refresh listener
        swipeRefreshLayout.setOnRefreshListener {
            loadPosts()
        }

        //This will load posts from server & save to local database
        loadPosts()
    }

    private fun loadPosts() {
        //Show ProgressBar
        showLoading()

        //Load posts from server
        postsViewModel.loadPosts().observe(this, Observer<List<Posts>> { posts ->
            posts?.forEach { post ->
                run {
                    Log.i(TAG, post.toString())

                    //Add posts to recycler view after converting to 'PostItem'
                    mAdapter.add(postsViewModel.transformToPostItem(post))
                    mAdapter.notifyAdapterDataSetChanged()

                    //Save posts to Database
                    postsViewModel.savePost(post)
                }
            }

            //Hides ProgressBar
            hideLoading()

            //This will check count of items in adapter, and will show view accordingly
            shouldHideOrShow(mAdapter.itemCount)
        })


        //Load saved posts from Database
        postsViewModel.loadSavedPosts().observe(this, Observer<List<Posts>> { posts ->
            posts?.forEach { post ->
                run {
                    Log.i(TAG, "Saved: " + post.toString())
                }
            }
        })
    }

    private fun initRecycleView() {

        //Setup FastAdapter with 'PostItem'
        mAdapter = postsViewModel.setupAdapter()

        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.setHasFixedSize(true)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = mAdapter

        mAdapter.withOnClickListener({ v, adapter, item, position ->
            val jobItem = mAdapter.getAdapterItem(position)
            navigateToDetails(jobItem, v?.cardView!!, PointF(v.x, v.y))
            false
        })
    }

    private fun showLoading() {
        if (!progress_bar.isShown && !swipeRefreshLayout.isRefreshing)
            progress_bar.visibility = View.VISIBLE

        empty_view.visibility = View.GONE
        swipeRefreshLayout.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        if (progress_bar.isShown)
            progress_bar.visibility = View.GONE

        if (swipeRefreshLayout.isRefreshing)
            swipeRefreshLayout.isRefreshing = false
    }

    private fun shouldHideOrShow(size: Int) {
        if (size == 0) {
            empty_view.visibility = View.VISIBLE
            swipeRefreshLayout.visibility = View.GONE
        } else {
            empty_view.visibility = View.GONE
            swipeRefreshLayout.visibility = View.VISIBLE
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun navigateToDetails(postItem: PostItem, v: View, touchPoint: PointF) {

        synchronized(mTransitionInformation) {
            mTransitionInformation.rememberTransition(v, touchPoint)
            v.visibility = View.VISIBLE
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, v, v.transitionName)
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_ITEM, postItem)
            startActivity(intent, options.toBundle())
        }

    }
}