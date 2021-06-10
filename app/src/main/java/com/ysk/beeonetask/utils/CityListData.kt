package com.ysk.beeonetask.utils
import androidx.annotation.Keep
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id



@Keep
@Entity
data class CityListData(val lat:String="",val lon:String="",val name:String="")
{
    @Id
    var id:Long?=null
}
