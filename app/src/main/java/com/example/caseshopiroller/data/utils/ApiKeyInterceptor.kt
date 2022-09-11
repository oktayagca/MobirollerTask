package com.example.caseshopiroller.data.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest: Request = chain.request()
        val requestWithUserAgent: Request = originRequest.newBuilder()
            .header("Api-Key", apiKey)
            .header("Alias-Key", aliasKey)
            .build()
        return chain.proceed(requestWithUserAgent)
    }
}