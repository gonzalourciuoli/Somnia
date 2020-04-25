package com.example.somnia.view
import com.example.somnia.R
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.somnia.controller.Controller
import kotlinx.android.synthetic.main.alarm_design.*


class New_alarmActivity : AppCompatActivity() {

    val controller = Controller()
    private lateinit var txtTitle: EditText
    private lateinit var timePicker: TimePicker
    private lateinit var mondaySwitch: Switch
    private lateinit var tuesdaySwitch: Switch
    private lateinit var wednesdaySwitch: Switch
    private lateinit var thursdaySwitch: Switch
    private lateinit var fridaySwitch: Switch
    private lateinit var saturdaySwitch: Switch
    private lateinit var sundaySwitch: Switch
    private lateinit var addButton: Button
    private lateinit var cancelButton: Button


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_alarm)

        txtTitle = findViewById(R.id.alarmTitle)
        timePicker = findViewById(R.id.timePicker)
        mondaySwitch = findViewById(R.id.monday_switch)
        tuesdaySwitch = findViewById(R.id.tuesday_switch)
        wednesdaySwitch= findViewById(R.id.wednesday_switch)
        thursdaySwitch = findViewById(R.id.thursday_switch)
        fridaySwitch = findViewById(R.id.friday_switch)
        saturdaySwitch = findViewById(R.id.saturday_switch)
        sundaySwitch = findViewById(R.id.sunday_switch)
        addButton = findViewById<Button>(R.id.add)
        cancelButton = findViewById(R.id.cancel)

        addButton.setOnClickListener {
            addAlarm()
            val intent = Intent(this@New_alarmActivity, AlarmsActivity::class.java)
            startActivity(intent)
        }

        cancelButton.setOnClickListener {
            val intent = Intent(this@New_alarmActivity, AlarmsActivity::class.java)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun addAlarm(){
        val weekDays: MutableMap<String, Boolean> = mutableMapOf<String, Boolean>()
        val title: String = txtTitle.text.toString()
        val hour: String = "Hour: "+timePicker.hour+ " Minute: "+ timePicker.minute
        mondaySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                weekDays.put("Monday",true)
            } else {
                weekDays.put("Monday",false)
            }
        }
        tuesdaySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                weekDays.put("Tuesday",true)
            } else {
                weekDays.put("Tuesday",false)
            }
        }
        wednesdaySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                weekDays.put("Wednesday",true)
            } else {
                weekDays.put("Wednesday",false)
            }
        }
        thursdaySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                weekDays.put("Thursday",true)
            } else {
                weekDays.put("Thursday",false)
            }
        }
        fridaySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                weekDays.put("Friday",true)
            } else {
                weekDays.put("Friday",false)
            }
        }
        saturdaySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                weekDays.put("Saturday",true)
            } else {
                weekDays.put("Saturday",false)
            }
        }
        sundaySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                weekDays.put("Sunday",true)
            } else {
                weekDays.put("Sunday",false)
            }
        }
        controller.addAlarm(title, hour, weekDays)


    }
}