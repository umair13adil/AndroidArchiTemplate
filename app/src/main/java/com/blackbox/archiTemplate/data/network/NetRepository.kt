package com.blackbox.archiTemplate.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.blackbox.archiTemplate.data.local.db.entity.Posts

/**
 * Created by umair on 10/01/2018.
 */
class NetRepository(val apiService: APIs) : NetDataSource {

    override fun loadPosts(): LiveData<List<Posts>> {

        return Transformations.map(apiService.getUsers(), { response -> response.body })

    }

}