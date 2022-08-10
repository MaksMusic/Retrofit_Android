package com.music.retrofit

import retrofit2.http.GET
import retrofit2.Call

interface ServisesApi {
    @GET("messages1.json")
    fun message() : Call<List<com.music.retrofit.Message>>
}