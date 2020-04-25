package com.example.somnia.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import com.example.somnia.controller.Controller

class Calendar : AppCompatActivity(), CalendarView.OnDateChangeListener {

        private val controller = Controller()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

            init()
    }

    private fun init(){
        val calendar = findViewById<CalendarView>(R.id.calendarView) as CalendarView
        calendar.setOnDateChangeListener(this)

        val ret = findViewById<Button>(R.id.returnButton) as Button
        ret.setOnClickListener {
            val intent = Intent(this@Calendar, Buttons_ListCalendar::class.java)
            startActivity(intent)
        }
    }

    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
        val builder = AlertDialog.Builder(this)
        val dia = dayOfMonth
        val mes = month + 1
        val año = year

        val id = año.toString() + "-" + mes.toString() + "-" + dia.toString()
        var informacio : String? = ""
        builder.setTitle("Valuation of " + dia + "/" + mes + "/" + año + ":\n")

        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        if (user != "") {
            informacio = controller.getValuationString(user.toString(), id)
            builder.setMessage(informacio)
            val dialog = builder.create()
            dialog.show()
        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }
}
