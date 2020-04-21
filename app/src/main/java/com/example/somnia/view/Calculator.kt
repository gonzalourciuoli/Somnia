package com.example.somnia.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import kotlin.properties.Delegates

public class Calculator : AppCompatActivity() {

    //private lateinit var cycleSwitch : Button
    private lateinit var timeWakeUp : EditText
    private lateinit var hoursToSleep : EditText
    private lateinit var  timeBed: EditText
    private var time1 : Double? = null
    private var hours : Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        //cycleSwitch = findViewById(R.id.cycleSwitch) as Switch
        timeWakeUp = findViewById(R.id.timeWakeUp)
        hoursToSleep = findViewById(R.id.hoursToSleep)
        timeBed = findViewById(R.id.timeBed)

        init()

    }

    private fun init(){
        val cycleSwitch = findViewById<Button>(R.id.cycleSwitch) as Switch
        cycleSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                this.time1 = (this.timeBed.text.toString()).toDouble()
                this.hours = (this.hoursToSleep.text.toString()).toDouble()
                var result = time1!! + hours!!
                if (result >= 24){
                    var i: Double
                    i = result - 24
                    result = i
                }
                Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show()
                //this.timeWakeUp.setText(result.toString())
            }
        }
    }
}