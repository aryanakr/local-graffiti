package com.aryanakbarpour.localgraffiti.data

import com.google.gson.annotations.SerializedName
import org.json.JSONArray

data class Art (
    val guid: String,
    val createdYear: String,
    val writersNames: String,
    val countryName: String,
    val countryCode: String,
    val cityName: String,
    val address: String,
    val status: String,
    @SerializedName("geoCoordinates")
    val geoCoordinates: GeoCoordinate,
    val linkOpenStreetMap: String,
    val pictLink: String
)

data class GeoCoordinate (
    val lat: Double,
    val lon: Double
)
