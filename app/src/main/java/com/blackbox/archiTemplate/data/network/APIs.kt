package com.blackbox.archiTemplate.data.network

import android.arch.lifecycle.LiveData
import com.blackbox.archiTemplate.data.local.db.entity.Posts
import retrofit2.http.GET

/**
 * Created by umair on 08/01/2018.
 */
interface APIs {

    @GET("posts")
    fun getUsers(): LiveData<ApiResponse<List<Posts>>>

}