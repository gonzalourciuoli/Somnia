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

class HorizonBarChart : AppCompatActivity(){

    val weeks = ArrayList<Int>()
    val hours2 = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizon_bar_chart)

        for (i in 1..4) {
            //168 son las horas que tiene una semana como máximo
            val value = Random.nextFloat() * 168
            hours2.add(value.toInt())
            weeks.add(i)
        }

        val horizonBarChart: AnyChartView = findViewById(R.id.horizontal_bar_chart)
        APIlib.getInstance().setActiveAnyChartView(horizonBarChart)
        createHorizonBarChart(horizonBarChart)
        horizonBarChart.setBackgroundColor("#9C7DFF")
    }

    fun createHorizonBarChart(horizonBarChart: AnyChartView){

        val bar = AnyChart.bar()
        val background = bar.background()
        val entries = ArrayList<DataEntry>()

        bar.animation(true)

        for (i in weeks.indices){
            entries.add(ValueDataEntry(weeks[i],hours2[i]))
        }

        bar.data(entries)
        bar.title("WEEKLY")
        bar.yAxis(0).title("Hours")
        bar.xAxis(0).title("Weeks")
        val rangeColors = arrayOf("#663399")
        bar.palette(rangeColors)
        background.stroke("#663399")
        background.fill(GradientKey("#dcd0ff",0,1))
        horizonBarChart.setChart(bar)

    }
}