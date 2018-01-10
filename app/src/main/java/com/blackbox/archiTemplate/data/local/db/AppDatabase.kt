package com.blackbox.archiTemplate.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.blackbox.archiTemplate.data.entity.Posts
import com.blackbox.archiTemplate.data.local.db.dao.PostsDao

/**
 * Created by umair on 10/01/2018.
 */

@Database(entities = arrayOf(Posts::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao
}