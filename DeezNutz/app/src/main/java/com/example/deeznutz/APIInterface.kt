package com.example.deeznutz


import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface APIInterface {

    @Headers("X-RapidAPI-Key: f44823c82fmsh11059c61f964f13p18c119jsn889bd7eceb49",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com",
        )

    @GET("search")
    fun getData(@Query("q") query: String): Call<MyData>
}