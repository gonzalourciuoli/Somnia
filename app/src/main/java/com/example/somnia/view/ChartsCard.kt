package com.example.somnia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.example.somnia.R

class ChartsCard : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var arrayList: ArrayList<LanguageItem> ?= null
    private var gridView : GridView ?= null
    private var languageAdapters : LanguageAdapters ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts_card)

        gridView = findViewById(R.id.my_grid)
        arrayList = ArrayList()
        arrayList = setDataList()
        languageAdapters = LanguageAdapters(applicationContext, arrayList!!)
        gridView?.adapter = languageAdapters
        gridView?.onItemClickListener = this

    }

    private fun setDataList() :ArrayList<LanguageItem>{

        var arrayList: ArrayList<LanguageItem> = ArrayList()

        arrayList.add(LanguageItem(R.drawable.bar_chart_icon,"MONTHLY"))
        arrayList.add(LanguageItem(R.drawable.horizon_bar_chart_icon,"WEEKLY"))
        arrayList.add(LanguageItem(R.drawable.pie_chart_icon,"DAILY"))
        arrayList.add(LanguageItem(R.drawable.linear_chart_icon,"AVERAGE"))

        return arrayList
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var items : LanguageItem = arrayList!!.get(position)
        Toast.makeText(applicationContext, items.name, Toast.LENGTH_LONG).show()

    }
}
