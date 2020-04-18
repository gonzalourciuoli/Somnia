package com.example.somnia.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.graphics.vector.GradientKey
import com.example.somnia.R

class PieChart: AppCompatActivity() {

    val phases = arrayOf("Light sleep","Deep sleep","REM sleep")
    val hours3 = intArrayOf(50,20,25)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)

        val pieChart: AnyChartView = findViewById<AnyChartView>(R.id.pie_chart)
        APIlib.getInstance().setActiveAnyChartView(pieChart)
        createPie(pieChart)

    }

    fun createPie(pieChart: AnyChartView){

        val pie = AnyChart.pie()
        val background = pie.background()
        val entries = ArrayList<DataEntry>()

        pie.animation(true)

        for (i in phases.indices){
            entries.add(ValueDataEntry(phases[i],hours3[i]))
        }

        pie.data(entries)
        pie.title("DAILY")
        val range_colors = arrayOf("#9575cd", "#ce93d8","#8e24aa")
        var palette = pie.palette(range_colors)
        background.stroke("#663399")
        background.fill(GradientKey("#dcd0ff",0,1))
        pieChart.setChart(pie)
    }
}