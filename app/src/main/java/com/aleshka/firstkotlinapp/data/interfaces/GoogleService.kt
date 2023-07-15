package com.aleshka.firstkotlinapp.data.interfaces

import com.aleshka.firstkotlinapp.data.models.GoogleResults
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleService {

    //    https://www.googleapis.com/customsearch/v1?key=AIzaSyDdBPCVzYyCmtFtZSSihqOSUsPZglM5x3E&cx=42a504d9a5afa4755&q=car&alt=json&searchType=image

    @GET("customsearch/v1")
    fun find(
        @Query("key") key: String?,
        @Query("cx") cx: String?,
        @Query("q") q: String?,
        @Query("alt") alt: String?,
        @Query("searchType") searchType: String?
    ): retrofit2.Call<GoogleResults?>?
}
