package com.aleshka.firstkotlinapp.data.interfaces

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object GoogleApi {

    private const val BASE_URL = "https://www.googleapis.com/"
    private var retrofit: Retrofit? = null
    private fun create(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val instance: Retrofit?
        get() {
            if (retrofit == null) retrofit = create()
            return retrofit
        }
}