package com.example.somnia.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.example.somnia.R
import com.example.somnia.controller.Controller
import com.example.somnia.model.AlarmReceiver
import kotlinx.android.synthetic.main.new_alarm.*

class AlarmMangerActivity : AppCompatActivity() {
    private lateinit var stopButton: Button
    private lateinit var alarmTitle: TextView
    private var controller = Controller()
    lateinit private var am: AlarmManager
    lateinit private var cont: Context
    lateinit private var pi: PendingIntent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_manager)
        init()
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        this.cont = this
        var intent_reciver: Intent = Intent(this, AlarmReceiver::class.java)

        stopButton =  findViewById(R.id.stopButton)
        alarmTitle =  findViewById(R.id.alarm)

        alarmTitle.text = "Alarm"


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