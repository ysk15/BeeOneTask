package com.ysk.beeonetask.home.model


import androidx.annotation.Keep

@Keep
data class CityDataResModel(
    val base: String? = null,
    val clouds: Clouds? = null,
    val cod: Int? = null,
    val coord: Coord? = null,
    val dt: Int? = null,
    val id: Int? = null,
    val main: Main? = null,
    val name: String? = null,
    val sys: Sys? = null,
    val visibility: Int? = null,
    val weather: List<Weather?>? = null,
    val wind: Wind? = null
) {
    @Keep
    data class Clouds(
        val all: Int? = null
    )

    @Keep
    data class Coord(
        val lat: Double? = null,
        val lon: Double? = null
    )

    @Keep
    data class Main(
        val humidity: Int? = null,
        val pressure: Int? = null,
        val temp: Double? = null,
        val temp_max: Double? = null,
        val temp_min: Double? = null
    )

    @Keep
    data class Sys(
        val country: String? = null,
        val id: Int? = null,
        val message: Double? = null,
        val sunrise: Int? = null,
        val sunset: Int? = null,
        val type: Int? = null
    )

    @Keep
    data class Weather(
        val description: String? = null,
        val icon: String? = null,
        val id: Int? = null,
        val main: String? = null
    )

    @Keep
    data class Wind(
        val deg: Int? = null,
        val speed: Double? = null
    )
}