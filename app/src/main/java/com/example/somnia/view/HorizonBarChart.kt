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

class HorizonBarChart : AppCompatActivity(){

    val weeks = ArrayList<Int>()
    val hours2 = ArrayList<Int>()

    private lateinit var auth : FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var dataCollection : CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizon_bar_chart)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        dataCollection = db.collection("horizon_bar_chart")

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

        dataCollection
            .get()
            .addOnSuccessListener {

                for (document in it) {
                    weeks.add(Integer.parseInt(document.get("xValue").toString()!!))
                    hours2.add(Integer.parseInt(document.get("yValue").toString()!!))
                }

                for (i in weeks.indices) {
                    entries.add(ValueDataEntry(weeks[i], hours2[i]))
                }

                bar.data(entries)
                bar.title("WEEKLY")
                bar.yAxis(0).title("Hours of sleep")
                bar.xAxis(0).title("Weeks")
                val rangeColors = arrayOf("#663399")
                bar.palette(rangeColors)
                background.stroke("#663399")
                //background.fill(GradientKey("#dcd0ff",0,1))
                horizonBarChart.setChart(bar)

            }

    }
}