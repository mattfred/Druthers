package com.example.druthers.api

import com.example.druthers.api.models.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for the Movie API
 */
interface MovieApi {
    /**
     * Get a movie by id.
     * @param token The API token.
     * @param i The movie id.
     */
    @GET("/")
    suspend fun getMovieById(@Query("apikey") token: String, @Query("i") i: String): Response<MovieDto>
}