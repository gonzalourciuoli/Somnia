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

class HorizonBarChart : AppCompatActivity(){

    var weeks = ArrayList<Int>()
    var hours2 = ArrayList<Float>()

    private lateinit var auth : FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var dataCollection : CollectionReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizon_bar_chart)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        dataCollection = db.collection("horizon_bar_chart")

        weeks = arrayListOf(1,2,3,4)

        val horizonBarChart: AnyChartView = findViewById(R.id.horizontal_bar_chart)
        APIlib.getInstance().setActiveAnyChartView(horizonBarChart)
        createHorizonBarChart(horizonBarChart)
        horizonBarChart.setBackgroundColor("#9C7DFF")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createHorizonBarChart(horizonBarChart: AnyChartView){

        val bar = AnyChart.bar()
        val background = bar.background()
        val entries = ArrayList<DataEntry>()

        bar.animation(true)

        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        val current = LocalDateTime.now()
        val current_mes : String = current.monthValue.toString()

        val week_1 = ArrayList<Float>()
        val week_2 = ArrayList<Float>()
        val week_3 = ArrayList<Float>()
        val week_4 = ArrayList<Float>()

        dataCollection.whereEqualTo("usuari", user)
            .get()
            .addOnSuccessListener {result->
                for (document in result){
                    val mes = document.get("mes").toString()
                    if (mes == current_mes){
                        if (document.get("semana") == "1"){
                            week_1.add(document.get("horas").toString().toFloat())
                        } else if (document.get("semana") == "2"){
                            week_2.add(document.get("horas").toString().toFloat())
                        } else if (document.get("semana") == "3"){
                            week_3.add(document.get("horas").toString().toFloat())
                        } else{
                            week_4.add(document.get("horas").toString().toFloat())
                        }
                    }
                }
                var horas_1 : Float = 0F
                var horas_2 : Float = 0F
                var horas_3 : Float = 0F
                var horas_4 : Float = 0F
                for (h in week_1){
                    horas_1 += h
                }
                for (h in week_2){
                    horas_2 += h
                }
                for (h in week_3){
                    horas_3 += h
                }
                for (h in week_4){
                    horas_4 += h
                }
                hours2.add(horas_1)
                hours2.add(horas_2)
                hours2.add(horas_3)
                hours2.add(horas_4)

                /*for (document in it) {
                    weeks.add(Integer.parseInt(document.get("xValue").toString()!!))
                    hours2.add(Integer.parseInt(document.get("yValue").toString()!!))
                }*/

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