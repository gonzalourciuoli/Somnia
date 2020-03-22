package com.example.somnia
/*
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.estadisticas.R.id.*
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonGraph = findViewById<Button>(R.id.button_graph)
        val buttonClock = findViewById<Button>(R.id.button_clock)
        val buttonCalculator = findViewById<Button>(R.id.button_calculator)
        val buttonSettings = findViewById<Button>(R.id.button_settings)

        val barGraph = findViewById<BarChart>(R.id.bar_graph)
        createBarChart(31,24, barGraph)

    }


    private fun createBarChart(bars: Int, range: Int , graph: BarChart){

        val xValues = ArrayList<BarEntry>()
        val spaceForBar = 3.0f

        for ( i in 0..bars){
            val value = Random.nextFloat()*range
            xValues.add(BarEntry(i*spaceForBar,value))
        }

        val set1 = BarDataSet(xValues,"Data Set1")
        set1.setColors(Color.MAGENTA)

        val data = BarData(set1)
        graph.data = data

    }

}
 */