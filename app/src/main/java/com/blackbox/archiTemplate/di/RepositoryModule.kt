package com.blackbox.archiTemplate.di

import com.blackbox.archiTemplate.data.local.db.AppDatabase
import com.blackbox.archiTemplate.data.local.db.dao.PostsDao
import com.blackbox.archiTemplate.data.local.posts.PostsDataSource
import com.blackbox.archiTemplate.data.local.posts.PostsRepository
import com.blackbox.archiTemplate.data.network.APIs
import com.blackbox.archiTemplate.data.network.NetDataSource
import com.blackbox.archiTemplate.data.network.NetRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Singleton

/**
 * Created by umair on 10/01/2018.
 */
@Module
class RepositoryModule{

    @Singleton
    @Provides
    fun provideNetDataSource(apiService: APIs): NetDataSource {
        return NetRepository(apiService)
    }

    @Singleton
    @Provides
    fun providePostsDataSource(executor: Executor, postsDao: PostsDao): PostsDataSource {
        return PostsRepository(executor, postsDao)
    }

    @Singleton
    @Provides
    fun providePostsDao(db: AppDatabase): PostsDao {
        return db.postsDao()
    }
}