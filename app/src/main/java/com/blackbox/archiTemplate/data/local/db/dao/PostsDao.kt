package com.blackbox.archiTemplate.data.local.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.blackbox.archiTemplate.data.local.db.entity.Posts

/**
 * Created by umair on 10/01/2018.
 */

@Dao
interface PostsDao {

    /**
     * Get a posts by id
     *
     * @param id The id of teh posts
     * @return posts from the table with the specific id
     * */
    @Query("SELECT * from posts WHERE id = :id")
    fun getUserById(id: String): LiveData<Posts>

    /**
     * Insert a posts to the database, if the posts already exist, replace it
     *
     * @param posts The posts tobe inserted
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(posts: Posts)


    /**
     * Delete all the users from the database
     * */
    @Query("DELETE FROM posts")
    fun deleteAllPosts()

    /**
     * Delete a single Posts from the Database
     *
     * @param posts The Posts to be deleted
     * */
    @Delete
    fun deletePost(posts: Posts)

    /**
     * Deletes a variable number of users
     * */
    @Delete
    fun deletePosts(vararg posts: Posts)

    /**
     * Loads all the posts from the db
     * */
    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<Posts>>
}