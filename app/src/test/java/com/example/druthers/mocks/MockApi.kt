package com.example.druthers.mocks

import com.example.druthers.api.MovieApi
import com.example.druthers.api.RestApiInterface
import com.example.druthers.api.models.MovieDto
import retrofit2.Response

class MockApi : RestApiInterface {
    override val client: MockMovieApi = MockMovieApi()
}

class MockMovieApi : MovieApi {
    override suspend fun getMovieById(token: String, i: String): Response<MovieDto> {
        val movie =  MovieDto(
            title = "The Matrix",
            poster = "https://m.media-amazon.com/images/M/MV5BMTYwOTQxODM5Ml5BMl5BanBnXkFtZTYwMzY5MzY3._V1_SX300.jpg",
            genre = "Action, Sci-Fi",
            type = "movie",
            year = "1999",
            rated = "R",
            plot = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
        )
        return Response.success(movie)
    }
}