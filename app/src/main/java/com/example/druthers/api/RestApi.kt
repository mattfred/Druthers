package com.example.druthers.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface RestApiInterface {
    val client: MovieApi
}

object RestApi: RestApiInterface {
    // base url
    private const val API_URL = "https://www.omdbapi.com"
    // content type
    private val contentType = "application/json".toMediaType()

    // used to log api calls
    private val interceptor = HttpLoggingInterceptor().apply {
        // this level returns the entire response and request body and headers
        level = HttpLoggingInterceptor.Level.BODY
    }

    // used to parse json
    private val json = Json {
        isLenient = true // ignore malformed json
        ignoreUnknownKeys = true // ignore unknown keys
    }

    override
    val client: MovieApi = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(
            okhttp3.OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        )
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(MovieApi::class.java)
}