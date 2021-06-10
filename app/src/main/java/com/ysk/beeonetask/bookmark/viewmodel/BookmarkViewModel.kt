package com.ysk.beeonetask.bookmark.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ysk.beeonetask.home.model.CityDataReqModel
import com.ysk.beeonetask.home.model.CityDataResModel
import com.ysk.beeonetask.home.repository.SearchRepository
import kotlinx.coroutines.launch
import java.io.IOException

class BookmarkViewModel : ViewModel() {
    val mdata = MutableLiveData<CityDataResModel>()


    fun getData(model: CityDataReqModel): LiveData<CityDataResModel> {
        viewModelScope.launch {
            try {
                val call = SearchRepository.getCityWeather(model)
                if (call != null) {
                    call.apply {
                        takeIf { isSuccessful } ?: throw Exception("Network Error")
                        takeIf { body() != null } ?: throw Exception("Server Error")
                        mdata.postValue(body())
                    }
                } else {
                    throw Exception("No Internet")
                }
            } catch (e: Exception) {
                mdata.postValue(null)
            }
        }
        return mdata
    }


}