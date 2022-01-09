package com.aryanakbarpour.localgraffiti.di

import com.aryanakbarpour.localgraffiti.data.GeoCoordinate
import com.aryanakbarpour.localgraffiti.data.remote.StreetArtApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer() {

//    Doesn't work for some god unknown reason
//    private val headerInterceptor = Interceptor { chain ->
//        var request = chain.request()
//
//        request.newBuilder()
//            .addHeader("x-rapidapi-host", "street-art.p.rapidapi.com")
//            .addHeader("x-rapidapi-key", "<yourapikey>")
//            .build()
//
//        val response = chain.proceed(request)
//        response
//    }
//
//    private val okHttp = OkHttpClient.Builder().addInterceptor(headerInterceptor)

    val retrofit: StreetArtApiService = Retrofit.Builder()
        .baseUrl("https://street-art.p.rapidapi.com/")
        //.client(okHttp.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StreetArtApiService::class.java)



}
