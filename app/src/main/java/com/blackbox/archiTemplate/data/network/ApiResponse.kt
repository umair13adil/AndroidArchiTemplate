package com.blackbox.archiTemplate.data.network

import android.support.annotation.IntRange
import android.util.Log
import retrofit2.Response
import java.io.IOException

/**
 * Created by umair on 10/01/2018.
 */
class ApiResponse<T> {

    var TAG = ApiResponse::class.java.simpleName

    @IntRange(from = 200, to = 299)
    val code: Int

    val body: T?

    val errorMessage: String?

    val isSuccessful: Boolean
        get() = code in 200..299

    constructor(error: Throwable) {
        code = 500
        body = null
        errorMessage = error.message
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().toString()
                } catch (ignored: IOException) {
                    Log.e(TAG, "error while parsing response: $ignored")
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }

    }

}
