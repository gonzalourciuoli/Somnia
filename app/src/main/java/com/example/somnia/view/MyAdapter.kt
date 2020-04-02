package com.example.somnia.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.somnia.R

class MyAdapter(var mCtx:Context, var resources: Int, var items:List<PresentationAlarm>): ArrayAdapter<PresentationAlarm>(mCtx,resources,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layOutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = layOutInflater.inflate(resources, null)

        val editAlarm : TextView = view.findViewById(R.id.alarm_hour)

        var mItem:PresentationAlarm = items[position]
        editAlarm.text = mItem.title

        return view
    }
}