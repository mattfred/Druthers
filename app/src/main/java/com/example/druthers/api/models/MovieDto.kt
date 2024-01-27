package com.example.druthers.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for Movie
 */
@Serializable
data class MovieDto(
    @SerialName("Title") val title: String? = "",
    @SerialName("Year") val year: String? = "",
    @SerialName("Rated") val rated: String? = "",
    @SerialName("Released") val released: String? = "",
    @SerialName("Season") val season: String? = "",
    @SerialName("Episode") val episode: String? = "",
    @SerialName("Runtime") val runtime: String? = "",
    @SerialName("Genre") val genre: String? = "",
    @SerialName("Director") val director: String? = "",
    @SerialName("Writer") val writer: String? = "",
    @SerialName("Actors") val actors: String? = "",
    @SerialName("Plot") val plot: String? = "",
    @SerialName("Language") val language: String? = "",
    @SerialName("Country") val country: String? = "",
    @SerialName("Awards") val awards: String? = "",
    @SerialName("Poster") val poster: String? = "",
    @SerialName("Ratings") val ratings: List<RatingDto?>? = listOf(),
    @SerialName("Metascore") val metascore: String? = "",
    @SerialName("imdbRating") val imdbRating: String? = "",
    @SerialName("imdbVotes") val imdbVotes: String? = "",
    @SerialName("imdbID") val imdbID: String? = "",
    @SerialName("seriesID") val seriesID: String? = "",
    @SerialName("Type") val type: String? = "",
    @SerialName("Response") val response: String? = "",
    @SerialName("DVD") val dVD: String? = "",
    @SerialName("BoxOffice") val boxOffice: String? = "",
    @SerialName("Production") val production: String? = "",
    @SerialName("Website") val website: String? = "",
    @SerialName("totalSeasons") val totalSeasons: String? = "",
)

@Serializable
data class RatingDto(
    @SerialName("Source") val source: String,
    @SerialName("Value") val value: String
)
