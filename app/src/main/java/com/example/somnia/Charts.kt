package com.example.somnia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import kotlin.random.Random

class Charts : AppCompatActivity() {

    val days = ArrayList<Int>()
    val hours1 = ArrayList<Int>()

    val weeks = ArrayList<Int>()
    val hours2 = ArrayList<Int>()

    val phases = arrayOf("Light sleep","Deep sleep","REM sleep")
    val hours3 = intArrayOf(50,20,25)

    val ages = ArrayList<Int>()
    val hours4 = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)

        for(i in 1..31){
            val value = Random.nextFloat() * 24
            hours1.add(value.toInt())
            days.add(i)
        }

        for(i in 1..4){
            //168 son las horas que tiene una semana como máximo
            val value = Random.nextFloat() * 168
            hours2.add(value.toInt())
            weeks.add(i)
        }

        for(i in 18..90){
            ages.add(i)
        }

        val barChart: AnyChartView = findViewById(R.id.bar_chart)
        APIlib.getInstance().setActiveAnyChartView(barChart)
        createBarChart(barChart)

        val horizonBarChart: AnyChartView = findViewById(R.id.horizontal_bar_chart)
        APIlib.getInstance().setActiveAnyChartView(horizonBarChart)
        createHorizonBarChart(horizonBarChart)

        val pieChart: AnyChartView = findViewById<AnyChartView>(R.id.pie_chart)
        APIlib.getInstance().setActiveAnyChartView(pieChart)
        createPie(pieChart)

        val lineChart : AnyChartView= findViewById(R.id.linear_chart)
        APIlib.getInstance().setActiveAnyChartView(lineChart)
        createRangeChart(lineChart)

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
        barChart.setChart(bar)

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
        horizonBarChart.setChart(bar)

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
        pieChart.setChart(pie)
    }

    fun createRangeChart(lineChart: AnyChartView){

        val line = AnyChart.line()
        val background = line.background()
        val entries = ArrayList<DataEntry>()

        line.animation(true)

        for (i in ages.indices){
            val value = Random.nextFloat()* 24
            hours4.add(value.toInt())
            entries.add(ValueDataEntry(ages[i],hours4[i]))
        }

        val set = Set.instantiate()
        set.data(entries)

        val lineMap: Mapping = set.mapAs("{ x: 'x', value: 'value' }")

        val l: Line = line.line(lineMap)
        l.name("People")
        l.hovered().markers().enabled(true)
        l.hovered().markers().type(MarkerType.CIRCLE).size(4.0)
        l.tooltip().position("right").anchor(Anchor.LEFT_CENTER).offsetX(5.0).offsetY(5.0)


        line.title("AVERAGE OF POPULATION")
        line.yAxis(0).title("Hours")
        line.xAxis(0).title("Age")
        line.legend(true)

        val range_colors = arrayOf("#9933FF")
        line.palette(range_colors)
        background.stroke("#663399")
        lineChart.setChart(line)
    }
}
