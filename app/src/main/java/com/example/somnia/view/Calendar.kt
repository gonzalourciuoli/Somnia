package com.example.somnia.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.renderscript.Int2
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import com.example.somnia.controller.Controller
import java.util.Calendar

public class Calendar : AppCompatActivity(), CalendarView.OnDateChangeListener {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

            /*val bundle : Bundle
            bundle = intent.extras!!
            bundle.getInt("day")
            bundle.getInt("month")
            bundle.getInt("year")*/

            val calendar = findViewById<CalendarView>(R.id.calendarView) as CalendarView
            calendar.setOnDateChangeListener(this)

            val ret = findViewById<Button>(R.id.returnButton) as Button
            ret.setOnClickListener {
                val intent = Intent(this@Calendar, Calculator::class.java)
                startActivity(intent)
            }
    }

    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
        val builder = AlertDialog.Builder(this)
        val dia = dayOfMonth
        val mes = month + 1
        val año = year

        builder.setTitle("Valuation of " + dia + "/" + mes + "/" + año + ":\n")
        builder.setMessage("Rating bar\n" + "Sport\n" + "Coffee\n" + "Alcohol\n" + "Comment\n")

        val dialog = builder.create()
        dialog.show()

    }
}
