package com.ysk.beeonetask.bookmark.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ysk.beeonetask.R
import com.ysk.beeonetask.bookmark.adapter.BookMarkListAdapter
import com.ysk.beeonetask.bookmark.viewmodel.BookmarkViewModel
import com.ysk.beeonetask.home.model.CityDataReqModel
import com.ysk.beeonetask.utils.CityListData
import com.ysk.beeonetask.utils.Constants.getvaluefromsp
import com.ysk.beeonetask.utils.DeleteClick
import com.ysk.beeonetask.utils.ItemClick
import com.ysk.beeonetask.utils.ObjectBox
import io.objectbox.Box
import kotlinx.android.synthetic.main.city_search_fragment.*
import kotlinx.android.synthetic.main.fragment_bookmark_list.*


class BookmarkList : Fragment(),ItemClick,DeleteClick {
    private lateinit var viewModel: BookmarkViewModel
    lateinit var bookBox:Box<CityListData>
    lateinit var adapter:BookMarkListAdapter
    val itemList= ArrayList<CityListData>()
    val weatherReqModel= CityDataReqModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmark_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar2.visibility=View.GONE
         bookBox = ObjectBox.get().boxFor(CityListData::class.java)
        val  manager = LinearLayoutManager(activity)
        adapter = BookMarkListAdapter(itemList,this,this)
        bookmarkList.adapter=adapter
        bookmarkList.layoutManager = manager
        if(bookBox.all.size>0){
        itemList.addAll(bookBox.all)
        adapter.notifyDataSetChanged()
        }
        else{
            Toast.makeText(activity,"No bookmarks found", Toast.LENGTH_SHORT).show()
        }

        viewModel.mdata.observe(viewLifecycleOwner, Observer {
            progressBar2.visibility=View.GONE
            val dialog = AlertDialog.Builder(activity)
            dialog.setTitle("Weather Today")
            val name = if (it.name!=null&&it.name!="") it.name else "N/A"
            val data = "Name: "+name+"\nCurrent: "+it.weather?.first()?.main+"\nDescription: "+it.weather?.first()?.description+
                    "\nTemp: "+it.main?.temp+"\nHumidity: "+it.main?.humidity+"\nWind Speed : "+it.wind?.speed
            dialog.setMessage(data)
            dialog.setNegativeButton("OK") { dia, _ ->
                dia.dismiss()
            }
            dialog.show()
        })
    }

    override fun iClick(item: CityListData) {
        if(item.lat!=""&&item.lon!=""){
            weatherReqModel.apply {
                lat=item.lat
                lon=item.lon
                unit=requireActivity().getvaluefromsp(requireActivity(),"unit")
            }
            progressBar2.visibility=View.VISIBLE
            viewModel.getData(weatherReqModel)
        }
        else{
            Toast.makeText(activity,"Location not found",Toast.LENGTH_SHORT).show()
        }
    }

    override fun dClick(item: CityListData) {
    bookBox.remove(item)
    itemList.clear()
    itemList.addAll(bookBox.all)
    adapter.notifyDataSetChanged()
        Toast.makeText(activity,"Location removed",Toast.LENGTH_SHORT).show()

    }


}