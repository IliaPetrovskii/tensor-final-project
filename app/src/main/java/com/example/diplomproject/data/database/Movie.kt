package com.example.diplomproject.data.database

import com.squareup.moshi.Json

data class Movie(
    @Json(name = "kinopoiskId")
    val kinopoiskId: Int,

    @Json(name = "nameRu")
    val nameRu: String,

    @Json(name = "year")
    val year: Int,

    @Json(name = "countries")
    val countries: List<Country>,

    @Json(name = "posterUrl")
    val posterUrl: String
)

data class Country(
    @Json(name = "country")
    val country: String
){
    override fun toString(): String {
        return country
    }
}