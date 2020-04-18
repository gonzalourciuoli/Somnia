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
import kotlin.random.Random

class BarChart : AppCompatActivity(){

    val days = ArrayList<Int>()
    val hours1 = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        for (i in 1..31) {
            val value = Random.nextFloat() * 24
            hours1.add(value.toInt())
            days.add(i)
        }

        val barChart: AnyChartView = findViewById(R.id.bar_chart)
        APIlib.getInstance().setActiveAnyChartView(barChart)
        createBarChart(barChart)
        barChart.setBackgroundColor("#000000")

    }

    fun createBarChart(barChart: AnyChartView){

        val bar = AnyChart.column()
        val background = bar.background()
        val entries = ArrayList<DataEntry>()

        bar.animation(true)

        for (i in days.indices){
            entries.add(ValueDataEntry(days[i],hours1[i]))
        }

        bar.data(entries)
        bar.title("MONTHLY")
        bar.yAxis(0).title("Hours")
        bar.xAxis(0).title("Days")
        val rangeColors = arrayOf("#663399")
        bar.palette(rangeColors)
        background.stroke("#663399")
        background.fill(GradientKey("#dcd0ff",0,1))
        barChart.setChart(bar)

    }
}