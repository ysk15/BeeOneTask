package com.ysk.beeonetask.bookmark.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ysk.beeonetask.R
import com.ysk.beeonetask.utils.CityListData
import com.ysk.beeonetask.utils.DeleteClick
import com.ysk.beeonetask.utils.ItemClick
import java.util.*


class BookMarkListAdapter(
     var namearray: List<CityListData>,
     var click: ItemClick,
     var dclick: DeleteClick
) : RecyclerView.Adapter<BookMarkListAdapter.MyViewHolder>() {

    inner class MyViewHolder(  v: View) : RecyclerView.ViewHolder(v) {
        var tv: TextView = v.findViewById(R.id.cityName)
        var delBtn: ImageView = v.findViewById(R.id.deleteBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookmark_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv.setOnClickListener {
            click.iClick(namearray[position])
        }
        holder.delBtn.setOnClickListener {
            dclick.dClick(namearray[position])
        }
        holder.tv.text = namearray[position].name
    }

    override fun getItemCount(): Int {
        return namearray.size
    }


}
