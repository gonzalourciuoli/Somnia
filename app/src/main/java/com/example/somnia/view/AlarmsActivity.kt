package com.example.somnia.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.somnia.R
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.somnia.model.Alarm
import com.example.somnia.model.AlarmsList

class AlarmsActivity : AppCompatActivity() {
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        val alarmsList = AlarmsList()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarms_list)
        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        val list = alarmsList.getAlarmsList()
        recyclerView.adapter = AdapterAlarm(list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton = findViewById<Button>(R.id.add_button)

        addButton.setOnClickListener {
            val intent = Intent(this@AlarmsActivity, New_alarmActivity::class.java)
            startActivity(intent)
        }

    }





        /*listView.adapter = MyAdapter(this, R.layout.alarm_design,list)
        listView.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->
        }

        val add_button = findViewById<Button>(R.id.add_button)
        add_button.setOnClickListener {
            val intent = Intent(this@AlarmsActivity, New_alarmActivity::class.java)
            startActivity(intent)
        }

        //recyclerView.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        val alarms=ArrayList<Alarm>()
        val daysArray=arrayOf("Monday", "Friday")
        val alarm=Alarm("5:45",daysArray)
        alarms.add(alarm)
        val alarm2=Alarm("18:34",daysArray)
        alarms.add(alarm2)
        val alarm3=Alarm("9:07",daysArray)
        alarms.add(alarm3)*/


}
