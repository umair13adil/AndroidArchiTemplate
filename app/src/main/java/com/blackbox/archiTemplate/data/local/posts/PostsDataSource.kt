package com.blackbox.archiTemplate.data.local.posts

import android.arch.lifecycle.LiveData
import com.blackbox.archiTemplate.data.local.db.entity.Posts

/**
 * Created by umair on 10/01/2018.
 */
interface PostsDataSource {

    fun insertPost(posts: Posts)

    fun getAllPosts(): LiveData<List<Posts>>
}