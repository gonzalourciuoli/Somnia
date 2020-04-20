package com.example.somnia.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
import com.anychart.graphics.vector.GradientKey
import com.example.somnia.R
import kotlin.random.Random

class LinearChart: AppCompatActivity()  {

    val ages = ArrayList<Int>()
    val hours4 = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_chart)

        for(i in 18..90){
            ages.add(i)
        }

        val lineChart : AnyChartView = findViewById(R.id.linear_chart)
        APIlib.getInstance().setActiveAnyChartView(lineChart)
        createRangeChart(lineChart)
        lineChart.setBackgroundColor("#9C7DFF")
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
        background.fill(GradientKey("#dcd0ff",0,1))
        lineChart.setChart(line)
    }

}