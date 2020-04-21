package com.example.somnia.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.renderscript.Int2
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import com.example.somnia.controller.Controller
import java.util.Calendar
import com.google.firebase.firestore.FirebaseFirestore

public class Calendar : AppCompatActivity(), CalendarView.OnDateChangeListener {

        private lateinit var db: FirebaseFirestore

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

            db = FirebaseFirestore.getInstance()

            init()

            /*val bundle : Bundle
            bundle = intent.extras!!
            bundle.getInt("day")
            bundle.getInt("month")
            bundle.getInt("year")*/

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
        builder.setTitle("Valuation of " + año + "-" + mes + "-" + dia + ":\n")

        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        if (user != "") {
            db.collection("valuations").document(user.toString()).collection(id).document("data")
                .get().addOnSuccessListener {
                    val date = it.get("date").toString()
                    val numStars = it.get("numStars").toString()
                    val sport_box = it.get("sport_box").toString()
                    val coffee_box = it.get("coffee_box").toString()
                    val alcohol_box = it.get("alcohol_box").toString()
                    val valuation_comment = it.get("valuation_comment").toString()

                    if (date == null || date == "null"){
                        builder.setMessage("No valuation on this day")
                        val dialog = builder.create()
                        dialog.show()
                    }else{
                        informacio += ("Date: " + date +"\n")
                        informacio += ("Rating: " + numStars + "/5 \n")
                        if (sport_box == "true"){
                            informacio += ("Sport: Yes \n")
                        }else{
                            informacio += ("Sport: No \n")
                        }
                        if (coffee_box == "true"){
                            informacio += ("Coffee: Yes \n")
                        }else{
                            informacio += ("Coffee: No \n")
                        }
                        if (alcohol_box == "true"){
                            informacio += ("Alcohol: Yes \n")
                        }else{
                            informacio += ("Alcohol: No \n")
                        }
                        if (valuation_comment != ""){
                            informacio += (valuation_comment)
                        }else{
                            informacio += ("No comments")
                        }
                        builder.setMessage(informacio)
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


    }
}
