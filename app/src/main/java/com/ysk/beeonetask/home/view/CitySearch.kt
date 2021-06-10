package com.ysk.beeonetask.home.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.ysk.beeonetask.R
import com.ysk.beeonetask.home.model.CityDataReqModel
import com.ysk.beeonetask.home.viewmodel.CitySearchViewModel
import com.ysk.beeonetask.utils.CityListData
import com.ysk.beeonetask.utils.Constants.getvaluefromsp
import com.ysk.beeonetask.utils.ObjectBox
import kotlinx.android.synthetic.main.city_search_fragment.*


class CitySearch : Fragment(),MapboxMap.OnMapClickListener {
   lateinit var mapView:MapView
    lateinit var map:MapboxMap
    val weatherReqModel=CityDataReqModel()
    var lat=""
    var lon=""
    companion object {
        fun newInstance() = CitySearch()
    }

    private lateinit var viewModel: CitySearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CitySearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.city_search_fragment, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility=View.GONE
        mapView =  view.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            map=mapboxMap
            map.addOnMapClickListener(this)
            map.setStyle(Style.MAPBOX_STREETS
            ) {

            }
        }
        weatherBtn.setOnClickListener {
            if(lat!=""&&lon!=""){
                weatherReqModel.apply {
                    lat=this@CitySearch.lat
                    lon=this@CitySearch.lon
                    unit=requireActivity().getvaluefromsp(requireActivity(),"unit")
                }
                progressBar.visibility=View.VISIBLE
            viewModel.getData(weatherReqModel)
            }
            else{
                Toast.makeText(activity,"Click on map to select location",Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.mdata.observe(viewLifecycleOwner, Observer {
            progressBar.visibility=View.GONE
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Weather Today")
            val name = if (it.name!=null&&it.name!="") it.name else "N/A"
            val data = "Name: "+name+"\nCurrent: "+it.weather?.first()?.main+"\nDescription: "+it.weather?.first()?.description+
                    "\nTemp: "+it.main?.temp+"\nHumidity: "+it.main?.humidity+"\nWind Speed : "+it.wind?.speed
            dialog.setMessage(data)
            dialog.setNegativeButton("OK") { dia, _ ->
                dia.dismiss()
            }
            dialog.setPositiveButton("Bookmark") { dia, _ ->
                val bookBox = ObjectBox.get().boxFor(CityListData::class.java)
                val item = CityListData(it.coord?.lat.toString(),it.coord?.lon.toString(),name)
                bookBox.put(item)
                Toast.makeText(activity,"Location added to bookmarks",Toast.LENGTH_SHORT).show()
                dia.dismiss()
            }
            dialog.show()
        })



    }

    override fun onMapClick(point: LatLng): Boolean {
        map.clear()
        lat=point.latitude.toString()
        lon=point.longitude.toString()
        map.addMarker(MarkerOptions().position(point))
        return false
    }

     override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

     override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

     override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

     override fun onStop() {
        super.onStop()
        mapView.onStop()
    }



    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

     override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }



}