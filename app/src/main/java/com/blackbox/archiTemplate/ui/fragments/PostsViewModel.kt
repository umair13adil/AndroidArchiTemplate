package com.blackbox.archiTemplate.ui.fragments

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.blackbox.archiTemplate.data.entity.Posts
import com.blackbox.archiTemplate.data.local.posts.PostsDataSource
import com.blackbox.archiTemplate.data.network.NetDataSource
import com.blackbox.archiTemplate.ui.items.PostItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import javax.inject.Inject

/**
 * Created by umair on 09/01/2018.
 */
class PostsViewModel @Inject
internal constructor(private val netDataSource: NetDataSource, var postsDataSource: PostsDataSource) : ViewModel() {

    fun setupAdapter(): FastItemAdapter<PostItem> {
        return FastItemAdapter<PostItem>()
    }

    //Convert post object received from data source to item for RecyclerView adapter
    fun transformToPostItem(post: Posts): PostItem {
        val postItem = PostItem()
        postItem.title = post.title
        postItem.details = post.details
        postItem.image = post.image
        return postItem
    }

    //Loads posts from server
    fun loadPosts(): LiveData<List<Posts>> {
        return netDataSource.loadPosts()
    }

    //Loads posts from local database
    fun loadSavedPosts(): LiveData<List<Posts>> {
        return postsDataSource.getAllPosts()
    }

    //Add post to local database
    fun savePost(post: Posts) {
        postsDataSource.insertPost(post)
    }
}