package com.aryanakbarpour.localgraffiti.data.remote

import com.aryanakbarpour.localgraffiti.data.Art
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val API_KEY = "<Your_API_Key>"

interface StreetArtApiService {
    @Headers(
        "x-rapidapi-host: street-art.p.rapidapi.com",
        "x-rapidapi-key: $API_KEY"
    )
    @GET("/city/{cityName}")
    suspend fun getCityArts(@Path("cityName") cityName: String): List<Art>
}