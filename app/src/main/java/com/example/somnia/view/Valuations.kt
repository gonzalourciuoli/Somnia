package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.somnia.R
//import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_valuations.*
import kotlinx.android.synthetic.main.login.view.*
import java.util.Calendar
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore

class Valuations : AppCompatActivity() {

    private lateinit var numStars : RatingBar
    private lateinit var sportBox : CheckBox
    private lateinit var coffeeBox : CheckBox
    private lateinit var alcoholBox : CheckBox
    private lateinit var valuation_comment : EditText
    private lateinit var date : Button
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valuations)

        numStars = findViewById<RatingBar>(R.id.ratingBar)
        sportBox = findViewById<CheckBox>(R.id.checkBox_sport)
        coffeeBox = findViewById<CheckBox>(R.id.checkBox_coffee)
        alcoholBox = findViewById<CheckBox>(R.id.checkBox_alcohol)
        valuation_comment = findViewById<EditText>(R.id.text_comment)
        date = findViewById<Button>(R.id.pickDate) as Button

        db = FirebaseFirestore.getInstance()

        init()
    }


    private fun init(){
        val save = findViewById<Button>(R.id.saveButton) as Button
        save.setOnClickListener {
            this.saveValuations()
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

            val intent = Intent(this@Valuations, InfoValuations::class.java)
            val intent2 = Intent(this@Valuations, Calendar::class.java)
            val intent3 = Intent(this@Valuations, Valuations::class.java)
            val bundle = Bundle()
            bundle.putInt("day", day)
            bundle.putInt("month", month)
            bundle.putInt("year", year)
            intent.putExtras(bundle)
            intent2.putExtras(bundle)
            intent3.putExtras(bundle)

            dpd.show()
        }
    }

    private fun saveValuations(){
        val numStars1 = ratingBar.rating
        val sport_box1 = checkBox_sport.isChecked
        val coffee_box1 = checkBox_coffee.isChecked
        val alcohol_box1 = checkBox_alcohol.isChecked
        val valuation_comment1 = text_comment.text.toString()
        val date1 = pickDate.text.toString()


        if (date1 == "Pick a date"){
            Toast.makeText(this, "You need to pick a date", Toast.LENGTH_LONG).show()
        } else {
            val valuation = hashMapOf(
                "date" to date1,
                "numStars" to numStars1,
                "sport_box" to sport_box1,
                "coffee_box" to coffee_box1,
                "alcohol_box" to alcohol_box1,
                "valuation_comment" to valuation_comment1
            )
            db.collection("valuations").document(date1).set(mapOf("valuation" to valuation))
            Toast.makeText(this, "Valuation saved", Toast.LENGTH_LONG).show()

            val intent = Intent(this@Valuations, Home::class.java)
            startActivity(intent)
        }
    }

}
