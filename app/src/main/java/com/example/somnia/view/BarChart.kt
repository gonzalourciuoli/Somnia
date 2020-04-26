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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random

class BarChart : AppCompatActivity(){

    val days = ArrayList<Int>()
    val hours1 = ArrayList<Int>()

    private lateinit var auth : FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var dataCollection : CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        dataCollection = db.collection("bar_chart")

        val barChart: AnyChartView = findViewById(R.id.bar_chart)
        APIlib.getInstance().setActiveAnyChartView(barChart)
        createBarChart(barChart)
        barChart.setBackgroundColor("#9C7DFF")

    }

    fun createBarChart(barChart: AnyChartView){

        val bar = AnyChart.column()
        val background = bar.background()
        val entries = ArrayList<DataEntry>()

        bar.animation(true)

        dataCollection
            .get()
            .addOnSuccessListener {

                for (document in it) {
                    days.add(Integer.parseInt(document.get("xValue").toString()!!))
                    hours1.add(Integer.parseInt(document.get("yValue").toString()!!))
                }

                for (i in days.indices) {
                    entries.add(ValueDataEntry(days[i], hours1[i]))
                }

                bar.data(entries)
                bar.title("MONTHLY")
                bar.yAxis(0).title("Hours of sleep")
                bar.xAxis(0).title("Days")
                val rangeColors = arrayOf("#663399")
                bar.palette(rangeColors)
                background.stroke("#663399")
                //background.fill(GradientKey("#dcd0ff",0,1))
                barChart.setChart(bar)

            }

    }
}