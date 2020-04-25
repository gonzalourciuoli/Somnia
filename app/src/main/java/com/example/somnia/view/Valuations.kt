package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.somnia.R
import kotlinx.android.synthetic.main.activity_valuations.*
import java.util.Calendar
import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.*
import com.example.somnia.controller.Controller

class Valuations : AppCompatActivity() {

    private lateinit var numStars : RatingBar
    private lateinit var sportBox : CheckBox
    private lateinit var coffeeBox : CheckBox
    private lateinit var alcoholBox : CheckBox
    private lateinit var valuation_comment : EditText
    private lateinit var date : Button
    private val controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valuations)
        init()
    }


    private fun init(){
        numStars = findViewById<RatingBar>(R.id.ratingBar)
        sportBox = findViewById<CheckBox>(R.id.checkBox_sport)
        coffeeBox = findViewById<CheckBox>(R.id.checkBox_coffee)
        alcoholBox = findViewById<CheckBox>(R.id.checkBox_alcohol)
        valuation_comment = findViewById<EditText>(R.id.text_comment)
        date = findViewById<Button>(R.id.pickDate) as Button

        val save = findViewById<Button>(R.id.saveButton) as Button
        save.setOnClickListener {
            this.saveValuations(it)
        }

        val cancel = findViewById<Button>(R.id.cancelButton) as Button
        cancel.setOnClickListener {
            val intent = Intent(this@Valuations, Home::class.java)
            startActivity(intent)
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        pickDate.setOnClickListener{
            val dpd = DatePickerDialog(this, R.style.DatePickerTheme,DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
                pickDate.text = "" + mYear + "-" + (mMonth+1) + "-" + mDay
            }, year, month, day)
            dpd.datePicker.maxDate = Calendar.getInstance().timeInMillis

            dpd.show()
        }
    }

    private fun saveValuations(view : View){
        val numStars1 = ratingBar.rating
        val sport_box1 = checkBox_sport.isChecked
        val coffee_box1 = checkBox_coffee.isChecked
        val alcohol_box1 = checkBox_alcohol.isChecked
        val valuation_comment1 = text_comment.text.toString()
        val date1 = pickDate.text.toString()


        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        if (user != "" || user != null) {
            if (date1 == "Pick a date"){
                Toast.makeText(this, "You need to pick a date", Toast.LENGTH_LONG).show()
            } else {
                controller.addValuation(user!!, date1, numStars1, sport_box1, coffee_box1, alcohol_box1, valuation_comment1)
                Toast.makeText(this, "Valuation saved", Toast.LENGTH_LONG).show()

                val userPreferences = view.context.getSharedPreferences("valuations", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = userPreferences.edit()

                editor.putString("date", date1)
                editor.apply()

                val intent = Intent(this@Valuations, Home::class.java)
                startActivity(intent)
            }
        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }

}
