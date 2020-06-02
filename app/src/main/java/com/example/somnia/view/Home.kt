package com.example.somnia.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.somnia.R
import com.example.somnia.controller.Controller
import com.example.somnia.model.Alarm
import com.example.somnia.model.AlarmReceiver
import java.util.Calendar

class Home : AppCompatActivity() {
    private lateinit var am: AlarmManager
    private lateinit var cont: Context
    private lateinit var pi: PendingIntent
    private lateinit var alarm: Alarm

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        this.cont = this
        var intent_reciver: Intent = Intent(this, AlarmReceiver::class.java)
        var calendar: Calendar = Calendar.getInstance()

        setContentView(R.layout.activity_home)
        val rate_last_sleep_button = findViewById<Button>(R.id.rate_last_sleep)
        rate_last_sleep_button.setOnClickListener {
            val intent = Intent(this@Home, Valuations::class.java)
            startActivity(intent)
        }

        val see_your_ratings_button = findViewById<Button>(R.id.see_your_ratings)
        see_your_ratings_button.setOnClickListener {
            val intent = Intent(this@Home, Buttons_ListCalendar::class.java)
            startActivity(intent)
        }

        val google_account = findViewById<Button>(R.id.google_account)
        google_account.setOnClickListener {
            val intent = Intent(this@Home, FitnessActivity::class.java)
            startActivity(intent)
        }

        val toggle_Wake_Sleep = findViewById<Switch>(R.id.toggle_Wake_Sleep)
        val timePicker_sleep_act = findViewById<TimePicker>(R.id.timePicker_sleep)
        val timePicker_wake_act = findViewById<TimePicker>(R.id.timePicker_wake)
        toggle_Wake_Sleep.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                toggle_Wake_Sleep.setText("Wake")
                timePicker_wake_act.visibility = View.VISIBLE
                timePicker_sleep_act.visibility = View.GONE
            } else {
                toggle_Wake_Sleep.setText("Sleep")
                timePicker_sleep_act.visibility = View.VISIBLE
                timePicker_wake_act.visibility = View.GONE
            }
        }

        val button_set_alarm = findViewById<Button>(R.id.button_setAlarm)
        var alarmTitle: String
        var alarmHour: Int
        var alarmMinute : Int
        button_set_alarm.setOnClickListener {
                if (toggle_Wake_Sleep.isChecked) {
                    alarmTitle = "WAKE UP"
                    alarmHour = timePicker_wake_act.hour
                    alarmMinute = timePicker_wake_act.minute
                } else {
                    alarmTitle = "GO TO SLEEP"
                    alarmHour = timePicker_sleep_act.hour
                    alarmMinute = timePicker_sleep_act.minute
                }

                var minute : String
                val controller = Controller()
                if (alarmMinute<10){
                    minute = "0" + alarmMinute.toString()
                }
                else{
                    minute = alarmMinute.toString()
                }
                val hour: String = alarmHour.toString() + ":" + minute
                val weekDays: MutableMap<String, Boolean> =mutableMapOf(
                    "Monday" to false,
                    "Tuesday" to false,
                    "Wednesday" to false,
                    "Thursday" to false,
                    "Friday" to false,
                    "Saturday" to false,
                    "Sunday" to false
                )
                val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
                val user = userPreferences.getString("email", "")
                val alarm = controller.addAlarm(alarmTitle, hour, weekDays,user!!)

                calendar.set(Calendar.HOUR_OF_DAY,alarmHour)
                calendar.set(Calendar.MINUTE, alarmMinute)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                intent_reciver.putExtra("on/off","on")
                intent_reciver.putExtra("alarmTitle",alarmTitle)
                pi = PendingIntent.getBroadcast(this@Home,0,intent_reciver,
                    PendingIntent.FLAG_UPDATE_CURRENT)
                am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pi)
                Toast.makeText(this, "New alarm added", Toast.LENGTH_LONG).show()
        }

    }
}
