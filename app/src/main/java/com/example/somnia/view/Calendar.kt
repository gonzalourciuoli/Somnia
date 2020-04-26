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
import com.example.somnia.model.Valuation
import com.google.firebase.firestore.FirebaseFirestore

class Calendar : AppCompatActivity(), CalendarView.OnDateChangeListener {

        private val controller = Controller()
        private lateinit var db : FirebaseFirestore

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

            init()
    }

    private fun init(){
        db = FirebaseFirestore.getInstance()
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

            //informacio = controller.getValuationString(user.toString(), id)

            var valuationsList = mutableListOf<Valuation>()
            db.collection("valuations")
                .whereEqualTo("user", user)
                .get().addOnSuccessListener {result ->
                    for (valuation in result){
                        val user = valuation.get("user").toString()
                        val date = valuation.get("date").toString()
                        val numStars = valuation.get("numStars").toString()
                        val sport_box = valuation.get("sport_box").toString()
                        val coffee_box = valuation.get("coffee_box").toString()
                        val alcohol_box = valuation.get("alcohol_box").toString()
                        val valuation_comment = valuation.get("valuation_comment").toString()

                        val valu = Valuation(user, date, numStars.toFloat(), sport_box.toBoolean(),
                            coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment)
                        valuationsList.add(valu!!)
                    }
                    var valu = ""
                    for (valuation in valuationsList){
                        if (valuation.getUserValuation() == user){
                            if (valuation.getDateValuation() == id){
                                valu = valuation.toString()
                            }
                        }
                    }

                    if (valu == ""){
                        builder.setMessage("No valuation on this day")
                        val dialog = builder.create()
                        dialog.show()
                    }else{
                        builder.setMessage(valu)
                        val dialog = builder.create()
                        dialog.show()
                    }
                }
        }
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }
}
