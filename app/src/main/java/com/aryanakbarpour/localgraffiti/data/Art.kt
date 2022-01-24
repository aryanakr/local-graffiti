package com.aryanakbarpour.localgraffiti.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
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

fun Art.toParcelizableArt() : ParcelizableArt{
    return ParcelizableArt(
        guid= this.guid,
        createdYear= this.createdYear,
        writersNames= this.writersNames,
        countryName= this.countryName,
        countryCode= this.countryCode,
        cityName= this.cityName,
        address= this.address,
        status= this.status,
        lat= this.geoCoordinates.lat,
        lon= this.geoCoordinates.lon,
        linkOpenStreetMap= this.linkOpenStreetMap,
        pictLink= this.pictLink
    )
}

@Parcelize
data class ParcelizableArt(
    val guid: String,
    val createdYear: String,
    val writersNames: String,
    val countryName: String,
    val countryCode: String,
    val cityName: String,
    val address: String,
    val status: String,
    val lat: Double,
    val lon: Double,
    val linkOpenStreetMap: String,
    val pictLink: String
) : Parcelable
