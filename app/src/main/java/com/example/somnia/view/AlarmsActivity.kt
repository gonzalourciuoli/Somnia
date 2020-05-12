package com.example.somnia.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.somnia.R
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.somnia.controller.Controller
import com.example.somnia.model.Alarm
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AlarmsActivity : AppCompatActivity() {
    private lateinit var addButton: Button
    private lateinit var controller: Controller
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarms_list)
        init()


        /*val weekDays: MutableMap<String, Boolean> = mutableMapOf(
            "Monday" to true,
            "Tuesday" to false,
            "Wednesday" to true,
            "Thursday" to false,
            "Friday" to false,
            "Saturday" to false,
            "Sunday" to false
        )
        val alarm1 = Alarm("title","2:09",weekDays)
        val alarm2 = Alarm("title","2:09",weekDays)
        alarmsList.add(alarm1)
        alarmsList.add(alarm2)*/





        addButton = findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            val intent = Intent(this@AlarmsActivity, New_alarmActivity::class.java)
            startActivity(intent)
        }


    }
    private fun init() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")
        var alarmsList = mutableListOf<Alarm>()
        db.collection("Alarms")
            .whereEqualTo("User", user)
            .get().addOnSuccessListener { list ->
                for (alarm in list) {
                    val title = alarm.get("Title").toString()
                    val hour = alarm.get("Hour").toString()
                    val status = alarm.get("Alarm on").toString()
                    val monday = alarm.get("Monday").toString()
                    val tuesday = alarm.get("Tuesday").toString()
                    val wednesday = alarm.get("Wednesday").toString()
                    val thursday = alarm.get("Thursday").toString()
                    val friday = alarm.get("Friday").toString()
                    val saturday = alarm.get("Saturday").toString()
                    val sunday = alarm.get("Sunday").toString()
                    val weekDays: MutableMap<String, Boolean> = mutableMapOf(
                        "Monday" to monday.toBoolean(),
                        "Tuesday" to tuesday.toBoolean(),
                        "Wednesday" to wednesday.toBoolean(),
                        "Thursday" to thursday.toBoolean(),
                        "Friday" to friday.toBoolean(),
                        "Saturday" to saturday.toBoolean(),
                        "Sunday" to sunday.toBoolean()
                    )
                    val alarm = Alarm(title, hour, weekDays, user!!)
                    alarm.setStatus(status.toBoolean())
                    alarmsList.add(alarm)
                }
                recyclerView.adapter = AdapterAlarm(alarmsList)
            }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
    fun getAlarmsList(): MutableList<Alarm>{
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser.toString()
        var alarmsList = mutableListOf<Alarm>()
        db.collection("Alarms").whereEqualTo("user", user).get().addOnSuccessListener { list ->
            for (alarm in list) {
                val title = alarm.get("Title").toString()
                val hour = alarm.get("Hour").toString()
                val status = alarm.get("Alarm on").toString()
                val monday = alarm.get("Monday").toString()
                val tuesday = alarm.get("Tuesday").toString()
                val wednesday = alarm.get("Wednesday").toString()
                val thursday = alarm.get("Thursday").toString()
                val friday = alarm.get("Friday").toString()
                val saturday = alarm.get("Saturday").toString()
                val sunday = alarm.get("Sunday").toString()
                val weekDays: MutableMap<String, Boolean> = mutableMapOf(
                    "Monday" to monday.toBoolean(),
                    "Tuesday" to tuesday.toBoolean(),
                    "Wednesday" to wednesday.toBoolean(),
                    "Thursday" to thursday.toBoolean(),
                    "Friday" to friday.toBoolean(),
                    "Saturday" to saturday.toBoolean(),
                    "Sunday" to sunday.toBoolean()
                )
                val alarm = Alarm(title, hour, weekDays, user!!)
                alarm.setStatus(status.toBoolean())
                alarmsList.add(alarm)
            }
        }
        return alarmsList
    }
}