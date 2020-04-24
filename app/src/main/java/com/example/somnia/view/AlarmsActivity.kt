package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.somnia.R
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.somnia.controller.Controller
import com.example.somnia.model.Alarm

class AlarmsActivity : AppCompatActivity() {
    private lateinit var addButton: Button
    private lateinit var controller: Controller


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarms_list)
        val recyclerView: RecyclerView = findViewById(R.id.recycler)

        //val list = controller.getAlarmsList()

        val weekDays: MutableMap<String, Boolean> = mutableMapOf(
            "Monday" to true,
            "Tuesday" to false,
            "Wednesday" to true,
            "Thursday" to false,
            "Friday" to false,
            "Saturday" to false,
            "Sunday" to false
        )
        val alarm1 = Alarm("title","2:09",weekDays)

        var list: MutableList<Alarm> = mutableListOf(alarm1)
            recyclerView.adapter = AdapterAlarm(list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton = findViewById<Button>(R.id.add_button)

        addButton.setOnClickListener {
            //val intent = Intent(this@AlarmsActivity, New_alarmActivity::class.java)
            //startActivity(intent)
        }

    }
}