package com.example.somnia.view
import android.content.Context
import com.example.somnia.R
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
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
        }

        cancelButton.setOnClickListener {
            val intent = Intent(this@New_alarmActivity, AlarmsActivity::class.java)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun addAlarm(){
        val weekDays: MutableMap<String, Boolean> = mutableMapOf()
        val alarmDays = IntArray(7)
        val title: String = txtTitle.text.toString()
        val hour: String = timePicker.hour.toString() + ":" + timePicker.minute.toString()
        if(mondaySwitch.isChecked) {
                weekDays.put("Monday",true)
                alarmDays[0] = 1
        } else {
                weekDays.put("Monday",false)
        }
        if(tuesdaySwitch.isChecked) {
                weekDays.put("Tuesday",true)
                alarmDays[1] = 2
            } else {
                weekDays.put("Tuesday",false)
            }

        if (wednesdaySwitch.isChecked) {
                weekDays.put("Wednesday",true)
                alarmDays[2] = 3
            } else {
                weekDays.put("Wednesday",false)
            }
        if(thursdaySwitch.isChecked) {
                weekDays.put("Thursday",true)
                alarmDays[3] = 4
            } else {
                weekDays.put("Thursday",false)
        }
        if(fridaySwitch.isChecked) {
                alarmDays[4] = 5
                weekDays.put("Friday",true)
            } else {
                weekDays.put("Friday",false)
        }
        if(saturdaySwitch.isChecked) {
                alarmDays[5] = 6
                weekDays.put("Saturday",true)
            } else {
                weekDays.put("Saturday",false)

        }
        if(sundaySwitch.isChecked) {
                alarmDays[6] = 7
                weekDays.put("Sunday",true)
            } else {
                weekDays.put("Sunday",false)

        }

        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        controller.addAlarm(title, hour, weekDays,user!!)

        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
        intent.putExtra(AlarmClock.EXTRA_HOUR, timePicker.hour)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, timePicker.minute)
        intent.putExtra(AlarmClock.EXTRA_DAYS, alarmDays)
        startActivity(intent)

        Thread.sleep(5000)

        val intent1 = Intent(this@New_alarmActivity, AlarmsActivity::class.java)
        startActivity(intent1)
    }
}