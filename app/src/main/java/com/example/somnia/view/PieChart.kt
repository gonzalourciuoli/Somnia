package com.example.somnia.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
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

class PieChart: AppCompatActivity() {

    val phases = arrayOf("Deep sleep","Light sleep","REM sleep")
    val hours3 = ArrayList<Float>()

    private lateinit var auth : FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var dataCollection : CollectionReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        dataCollection = db.collection("pie_chart")

        val pieChart: AnyChartView = findViewById<AnyChartView>(R.id.pie_chart)
        APIlib.getInstance().setActiveAnyChartView(pieChart)
        createPie(pieChart)
        pieChart.setBackgroundColor("#9C7DFF")

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createPie(pieChart: AnyChartView){

        val pie = AnyChart.pie()
        val background = pie.background()
        val entries = ArrayList<DataEntry>()

        pie.animation(true)

        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        val current = LocalDateTime.now()
        val data : String = current.year.toString() + "-" + current.monthValue.toString() + "-" + current.dayOfMonth.toString()

        dataCollection.document(user.toString() + "@" + data)
            .get()
            .addOnSuccessListener {
                hours3.add(it.get("Deep sleep").toString().toFloat())
                hours3.add(it.get("Light sleep").toString().toFloat())
                hours3.add(it.get("REM sleep").toString().toFloat())
                /*for (document in it) {
                    hours3.add(Integer.parseInt(document.get("value").toString()!!).toFloat())
                }*/

                for (i in phases.indices) {
                    entries.add(ValueDataEntry(phases[i], hours3[i]))
                }

                pie.data(entries)
                pie.title("DAILY")
                val range_colors = arrayOf("#9575cd", "#ce93d8", "#8e24aa")
                var palette = pie.palette(range_colors)
                background.stroke("#663399")
                //background.fill(GradientKey("#dcd0ff",0,1))
                pieChart.setChart(pie)

            }
    }
}