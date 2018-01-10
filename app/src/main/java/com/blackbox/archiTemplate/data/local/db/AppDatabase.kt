package com.blackbox.archiTemplate.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.blackbox.archiTemplate.data.local.db.dao.PostsDao
import com.blackbox.archiTemplate.data.local.db.entity.Posts

/**
 * Created by umair on 10/01/2018.
 */

@Database(entities = arrayOf(Posts::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao
}