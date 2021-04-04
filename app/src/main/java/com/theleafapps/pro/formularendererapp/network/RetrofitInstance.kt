package com.theleafapps.pro.formularendererapp.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private const val base_url = "https://en.wikipedia.org/api/rest_v1/"

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
                .setLenient()
                .create()))
            .build()
    }

    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}