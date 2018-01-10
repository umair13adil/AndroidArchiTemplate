package com.blackbox.archiTemplate.data.network

import android.arch.lifecycle.LiveData
import com.blackbox.archiTemplate.data.entity.Posts

/**
 * Created by umair on 10/01/2018.
 */
interface NetDataSource {
    fun loadPosts(): LiveData<List<Posts>>
}
