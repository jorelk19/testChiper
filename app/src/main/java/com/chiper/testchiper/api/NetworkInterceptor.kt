package com.chiper.testchiper.api

import android.content.Context
import com.chiper.testchiper.App
import com.chiper.testchiper.R
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import java.io.IOException
import java.net.SocketTimeoutException

class NetworkInterceptor(private val context: Context) :
    Interceptor {

    companion object {
        const val AUTHORIZATION_BEARER = "Authorization"
        const val CONTENT_TYPE_HEADER = "Content-Type"
        const val CONTENT_TYPE = "application/json;charset=utf-8"
        const val TIME_OUT_EXCEPTION_MESSAGE = "Timeout exception"
    }

    @Throws(IOException::class, SocketTimeoutException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            var request = chain.request()
            request = addDynamicHeadersToRequest(request)
            return chain.proceed(request)
        } catch (e: SocketTimeoutException) {
            throw SocketTimeoutException(TIME_OUT_EXCEPTION_MESSAGE)
        } catch (e: JSONException) {
            throw SocketTimeoutException(TIME_OUT_EXCEPTION_MESSAGE)
        }
    }

    private fun addDynamicHeadersToRequest(request: Request): Request {
        return request.newBuilder()
            .addHeader(AUTHORIZATION_BEARER, "Bearer " + App.getAppContext().getString(R.string.app_api_key))
            .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE)
            .build()
    }
}
