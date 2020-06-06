package com.example.somnia.view
import android.app.AlarmManager
import android.app.PendingIntent
import android.bluetooth.BluetoothManager
import android.content.ComponentCallbacks2
import android.content.Context
import com.example.somnia.R
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.AlarmClock
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.somnia.controller.Controller
import com.example.somnia.model.Alarm
import com.example.somnia.model.AlarmReceiver
import com.spark.submitbutton.SubmitButton
import kotlinx.android.synthetic.main.alarm_design.*
import java.util.Calendar


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
    private lateinit var addButton: SubmitButton
    private lateinit var cancelButton: Button
    private lateinit var am: AlarmManager
    private lateinit var cont: Context
    private lateinit var pi: PendingIntent
    private lateinit var alarm: Alarm


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_alarm)
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        this.cont = this
        txtTitle = findViewById(R.id.alarmTitle)
        timePicker = findViewById(R.id.timePicker)
        mondaySwitch = findViewById(R.id.monday_switch)
        tuesdaySwitch = findViewById(R.id.tuesday_switch)
        wednesdaySwitch= findViewById(R.id.wednesday_switch)
        thursdaySwitch = findViewById(R.id.thursday_switch)
        fridaySwitch = findViewById(R.id.friday_switch)
        saturdaySwitch = findViewById(R.id.saturday_switch)
        sundaySwitch = findViewById(R.id.sunday_switch)
        addButton = findViewById(R.id.add)
        cancelButton = findViewById(R.id.cancel)
        var intent_reciver: Intent = Intent(this, AlarmReceiver::class.java)
        var calendar: Calendar = Calendar.getInstance()


        addButton.setOnClickListener {
            addAlarm()
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            calendar.set(Calendar.MINUTE, timePicker.minute)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            intent_reciver.putExtra("on/off","on")
            intent_reciver.putExtra("alarmTitle",txtTitle.text.toString())
            pi = PendingIntent.getBroadcast(this@New_alarmActivity,0,intent_reciver,PendingIntent.FLAG_UPDATE_CURRENT)
            am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pi)
            Handler().postDelayed({ startActivity(Intent(this@New_alarmActivity, AlarmsActivity::class.java)) }, 2000)
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
        var minute = ""
        if (timePicker.minute<10){
            minute = "0" + timePicker.minute.toString()
        }
        else{
            minute = timePicker.minute.toString()
        }
        val hour: String = timePicker.hour.toString() + ":" + minute
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
        alarm = controller.addAlarm(title, hour, weekDays,user!!)






        /*val intent = Intent(AlarmClock.ACTION_SET_ALARM)
        intent.putExtra(AlarmClock.EXTRA_HOUR, timePicker.hour)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, timePicker.minute)
        intent.putExtra(AlarmClock.EXTRA_DAYS, alarmDays)
        startActivity(intent)

        Thread.sleep(5000)

        val intent1 = Intent(this@New_alarmActivity, AlarmsActivity::class.java)
        startActivity(intent1)*/
    }


     fun stopAlarm(){
         intent.putExtra("on/off","off")
        pi = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        am.cancel(pi)

    }
}