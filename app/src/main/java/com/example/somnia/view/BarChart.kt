package com.example.somnia.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
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
import java.time.LocalDateTime
import kotlin.random.Random

class BarChart : AppCompatActivity(){

    val days = ArrayList<Int>()
    val hours1 = ArrayList<Float>()

    private lateinit var auth : FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var dataCollection : CollectionReference

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun createBarChart(barChart: AnyChartView){

        val bar = AnyChart.column()
        val background = bar.background()
        val entries = ArrayList<DataEntry>()

        bar.animation(true)

        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        val current = LocalDateTime.now()
        val data : String = current.year.toString() + "-" + current.monthValue.toString() + "-" + current.dayOfMonth.toString()

        dataCollection.whereEqualTo("usuari", user)
            .get()
            .addOnSuccessListener {result ->
                for (document in result){
                    if (document.get("mes") == current.monthValue.toString()){
                        days.add(document.get("dia").toString().toInt())
                        hours1.add(document.get("horas").toString().toFloat())
                    }
                }

                /*for (document in it) {
                    days.add(Integer.parseInt(document.get("xValue").toString()!!))
                    hours1.add(Integer.parseInt(document.get("yValue").toString()!!))
                }*/

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