package com.blackbox.archiTemplate.data.local.posts

import android.arch.lifecycle.LiveData
import com.blackbox.archiTemplate.data.local.db.dao.PostsDao
import com.blackbox.archiTemplate.data.local.db.entity.Posts
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * Created by umair on 10/01/2018.
 */
class PostsRepository @Inject constructor(var executor: Executor, var postDao: PostsDao) : PostsDataSource {

    override fun insertPost(posts: Posts) {
        executor.execute {
            postDao.insertPost(posts)
        }
    }

    override fun getAllPosts(): LiveData<List<Posts>> {
        return postDao.getAllPosts()
    }
}