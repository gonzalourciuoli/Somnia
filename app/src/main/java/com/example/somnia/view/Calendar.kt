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
        //perque no li pases la vista
        //tot aixo ho fas al controlador y li pases la instancia d'aquesta clase i ho cambies alla aixi no has de fer return es la manera cutre de fer-ho'

        if (user != "") {
            //informacio = controller.getValuationString(user.toString(), id)
            db.collection("valuations").document(user.toString() + "@" + id)
                .get().addOnSuccessListener {
                    val date = it.get("date").toString()
                    val numStars = it.get("numStars").toString()
                    val sport_box = it.get("sport_box").toString()
                    val coffee_box = it.get("coffee_box").toString()
                    val alcohol_box = it.get("alcohol_box").toString()
                    val valuation_comment = it.get("valuation_comment").toString()
                    val valu = Valuation(user!!, date, numStars.toFloat(), sport_box.toBoolean(),
                        coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment)
                    if (valu.getDateValuation() == id){
                        builder.setMessage(valu.toString())
                        val dialog = builder.create()
                        dialog.show()
                    }else{
                        builder.setMessage("No valuation on this day")
                        val dialog = builder.create()
                        dialog.show()
                    }

                }
                .addOnFailureListener {
                    builder.setMessage("No valuation on this day")
                    val dialog = builder.create()
                    dialog.show()
                }
        }
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }
}
