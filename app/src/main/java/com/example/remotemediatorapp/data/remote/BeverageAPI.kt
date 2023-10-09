package com.example.remotemediatorapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BeverageAPI {

    @GET("beers")
    suspend fun getBeverages(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ):List<BeverageDto>

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}