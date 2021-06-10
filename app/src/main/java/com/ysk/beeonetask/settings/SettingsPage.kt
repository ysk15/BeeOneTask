package com.ysk.beeonetask.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ysk.beeonetask.R
import com.ysk.beeonetask.utils.CityListData
import com.ysk.beeonetask.utils.Constants.putvaluetosp
import com.ysk.beeonetask.utils.ObjectBox
import kotlinx.android.synthetic.main.fragment_settings_page.*


class SettingsPage : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        metricBtn.setOnClickListener {
         requireActivity().putvaluetosp(requireActivity(),"unit","metric")
            Toast.makeText(activity,"Metric units are set", Toast.LENGTH_SHORT).show()
        }
        imperialBtn.setOnClickListener {
            requireActivity().putvaluetosp(requireActivity(),"unit","imperial")
            Toast.makeText(activity,"Imperial units are set", Toast.LENGTH_SHORT).show()
        }
        clearBtn.setOnClickListener {
            ObjectBox.get().boxFor(CityListData::class.java).removeAll()
            Toast.makeText(activity,"All bookmarks removed", Toast.LENGTH_SHORT).show()
        }

    }


}