package com.example.druthers.mocks

import com.example.druthers.api.MovieRepositoryInterface
import com.example.druthers.models.Movie

class MockMovieRepository : MovieRepositoryInterface {
    override suspend fun getMovieById(i: String): Movie {
        return Movie(
            title = "The Matrix",
            poster = "https://m.media-amazon.com/images/M/MV5BMTYwOTQxODM5Ml5BMl5BanBnXkFtZTYwMzY5MzY3._V1_SX300.jpg",
            genre = "Action, Sci-Fi",
            type = "movie",
            year = "1999",
            rated = "R",
            plot = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
        )
    }
}