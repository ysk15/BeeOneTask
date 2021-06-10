package com.ysk.beeonetask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mapbox.mapboxsdk.Mapbox
import com.ysk.beeonetask.bookmark.view.BookmarkList
import com.ysk.beeonetask.home.view.CitySearch
import com.ysk.beeonetask.settings.SettingsPage
import com.ysk.beeonetask.utils.Constants.getvaluefromsp
import com.ysk.beeonetask.utils.Constants.putvaluetosp
import com.ysk.beeonetask.utils.ObjectBox
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            if(this.getvaluefromsp(this,"unit")==""){
                this.putvaluetosp(this,"unit","metric")
            }
            Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
            ObjectBox.init(applicationContext)
        } catch (e: Exception) {
        }
        this.supportFragmentManager.beginTransaction().replace(R.id.frame,CitySearch()).commit()
        home.setOnClickListener {
            this.supportFragmentManager.beginTransaction().replace(R.id.frame,CitySearch()).commit()
        }
        settings.setOnClickListener {
            this.supportFragmentManager.beginTransaction().replace(R.id.frame,SettingsPage()).commit()
        }
        bookmark.setOnClickListener {
            this.supportFragmentManager.beginTransaction().replace(R.id.frame,BookmarkList()).commit()
        }


    }
}