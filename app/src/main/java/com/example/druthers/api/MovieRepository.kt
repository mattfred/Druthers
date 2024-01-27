package com.example.druthers.api

import com.example.druthers.models.Movie

/**
 * Movie Repository Interface
 */
interface MovieRepositoryInterface {
    /**
     * Get Movie by Id
     */
    suspend fun getMovieById(i: String): Movie?
}

/**
 * Movie Repository Implementation
 * @param restApi: RestApiInterface - Interface for RestApi
 */
class MovieRepository(
    private val restApi: RestApiInterface = RestApi
) : MovieRepositoryInterface {
    // api token
    private val token = "4d9e0ca"

    override suspend fun getMovieById(i: String): Movie? {
        restApi.client.getMovieById(token, i).let { response ->
            // if response is successful, convert to Movie object and return
            if (response.isSuccessful) {
                response.body()?.let { movieDto ->
                    return Movie(
                        title = movieDto.title ?: "",
                        poster = movieDto.poster ?: "",
                        genre = movieDto.genre ?: "",
                        type = movieDto.type ?: "",
                        year = movieDto.year ?: "",
                        rated = movieDto.rated ?: "",
                        plot = movieDto.plot ?: "",
                    )
                }
            }
            return null
        }
    }
}