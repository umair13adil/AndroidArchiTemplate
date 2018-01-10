package com.blackbox.archiTemplate.ui.activities


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.blackbox.archiTemplate.data.local.db.entity.Posts
import com.blackbox.archiTemplate.data.network.NetDataSource
import javax.inject.Inject

/**
 * Created by umair on 10/01/2018.
 */

class MainViewModel @Inject
internal constructor(private val netDataSource: NetDataSource) : ViewModel() {

    fun loadUsers(): LiveData<List<Posts>> {
        return netDataSource.loadPosts()
    }

}
