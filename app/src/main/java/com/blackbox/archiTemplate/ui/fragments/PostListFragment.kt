package com.blackbox.archiTemplate.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blackbox.archiTemplate.R
import com.blackbox.archiTemplate.data.local.db.entity.Posts
import com.blackbox.archiTemplate.ui.items.PostItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import javax.inject.Inject

/**
 * Created by umair on 08/01/2018.
 */
class PostListFragment : Fragment() {

    var TAG = PostListFragment::class.java.simpleName

    private lateinit var postsViewModel: PostsViewModel

    private lateinit var mAdapter: FastItemAdapter<PostItem>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {

        fun newInstance(): PostListFragment {
            return PostListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "Load Posts..")

        postsViewModel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)

        postsViewModel.loadPosts().observe(this, Observer<List<Posts>> { posts ->
            posts?.forEach { post ->
                run {
                    Log.i(TAG, post.toString())

                    mAdapter.add(postsViewModel.transformToPostItem(post))
                    mAdapter.notifyAdapterDataSetChanged()

                    postsViewModel.savePost(post)
                }
            }
        })


        postsViewModel.loadSavedPosts().observe(this, Observer<List<Posts>> { posts ->
            posts?.forEach { post ->
                run {
                    Log.i(TAG, "Saved: " + post.toString())
                }
            }
        })

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize RecyclerView with click listeners
        initRecycleView()
    }

    private fun initRecycleView() {

        mAdapter = postsViewModel.setupAdapter()

        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.setHasFixedSize(true)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = mAdapter

        mAdapter.withOnClickListener({ v, adapter, item, position ->
            val jobItem = mAdapter.getAdapterItem(position)
            //navigateToDetails(jobItem, v.cardView, PointF(v.x, v.y))
            false
        })
    }
}