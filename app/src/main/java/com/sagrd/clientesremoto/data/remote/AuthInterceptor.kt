package com.sagrd.clientesremoto.data.remote

import okhttp3.Interceptor

class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Auth-Token", token)
            .build()

        return chain.proceed(request)
    }

}