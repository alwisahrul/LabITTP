package com.alwi.labittp

import retrofit2.Call
import retrofit2.http.GET

interface ServiceInterface {
    @GET("API")
    fun getData(): Call<List<LaporanData>>
}