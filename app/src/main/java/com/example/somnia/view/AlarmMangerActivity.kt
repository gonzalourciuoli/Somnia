package com.example.somnia.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import com.example.somnia.R
import com.example.somnia.controller.Controller
import com.example.somnia.model.AlarmReceiver
import kotlinx.android.synthetic.main.new_alarm.*
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.*

class AlarmMangerActivity : AppCompatActivity() {
    private lateinit var stopButton: Button
    private lateinit var alarmTitle: TextView
    private var controller = Controller()
    lateinit private var am: AlarmManager
    lateinit private var cont: Context
    lateinit private var pi: PendingIntent
    private lateinit var dateTxt: TextView
    private lateinit var hourTxt: TextView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_manager)
        init()
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        this.cont = this
        var intent_reciver: Intent = Intent(this, AlarmReceiver::class.java)

        stopButton =  findViewById(R.id.stopButton)
        alarmTitle =  findViewById(R.id.alarm)
        val title = intent_reciver!!.getStringExtra("alarmTitle")
        alarmTitle.text = "Alarm"

        dateTxt = findViewById(R.id.textView_Date)
        hourTxt = findViewById(R.id.textView_Hour)
        val current = LocalDateTime.now()
        //val date = current.substringBefore("T")
        var hour = current.toString().substringAfter("T")
        hour = hour.substringBeforeLast(":")
        dateTxt.text = current.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ", " + current.dayOfMonth + " " +
        current.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + current.year
        hourTxt.text = hour
        stopButton.setOnClickListener {
            intent_reciver.putExtra("on/off","off")
            pi = PendingIntent.getBroadcast(this,0,intent_reciver,PendingIntent.FLAG_UPDATE_CURRENT)
            am.cancel(pi)
            sendBroadcast(intent_reciver)
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun init(){

    }

    }