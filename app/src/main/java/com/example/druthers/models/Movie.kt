package com.example.druthers.models

import java.io.Serializable

/**
 * Movie data class
 */
data class Movie(
    val title: String,
    val poster: String,
    val genre: String,
    val type: String,
    val year: String,
    val rated: String,
    val plot: String,
) : Serializable
