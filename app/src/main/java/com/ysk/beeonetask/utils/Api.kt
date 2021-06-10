package com.ysk.beeonetask.utils

import com.ysk.beeonetask.home.model.CityDataResModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    suspend fun getCityData(@Query("lat") name:String,
                            @Query("lon") lon:String,
                            @Query("appid") appId:String,
                            @Query("units") units:String,
                            ): Response<CityDataResModel?>?
}