package com.example.druthers.api

import com.example.druthers.mocks.MockApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieRepositoryTest {

    @Test
    fun getMovieByIdTest() = runBlocking {

        val restApi = MockApi()

        restApi.client.getMovieById("4d9e0ca", "tt0133093").let { response ->
            if (response.isSuccessful) {
                response.body()?.let { movieDto ->
                    assert(movieDto.title == "The Matrix")
                }
            }
        }
    }
}