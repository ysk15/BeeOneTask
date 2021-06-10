package com.ysk.beeonetask.home.repository

import com.ysk.beeonetask.home.model.CityDataReqModel
import com.ysk.beeonetask.utils.ApiClient

object SearchRepository {
    suspend fun getCityWeather(model: CityDataReqModel) = ApiClient.create().getCityData(model.lat,model.lon,"e7c1f341d343a4476f8fd0be0b125c3c",model.unit)
}