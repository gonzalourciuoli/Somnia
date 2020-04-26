package com.example.somnia.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.somnia.R
import com.example.somnia.controller.Controller
import com.example.somnia.model.Valuation
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class InfoValuations : AppCompatActivity(){

    private lateinit var db: FirebaseFirestore
    private  var controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_valuations)

        db = FirebaseFirestore.getInstance()

        init()
    }

    private fun init() {
        val listView: RecyclerView = findViewById(R.id.valuations_list)

        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        var valuationsList = mutableListOf<Valuation>()

        if (user != "") {
            db.collection("valuations")
                .whereEqualTo("user", user)
                .get().addOnSuccessListener {result ->
                    for (valuation in result){
                        val date = valuation.get("date").toString()
                        val numStars = valuation.get("numStars").toString()
                        val sport_box = valuation.get("sport_box").toString()
                        val coffee_box = valuation.get("coffee_box").toString()
                        val alcohol_box = valuation.get("alcohol_box").toString()
                        val valuation_comment = valuation.get("valuation_comment").toString()
                        val valu = Valuation(user!!, date, numStars.toFloat(), sport_box.toBoolean(),
                                coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment)
                        valuationsList.add(valu)
                    }
                    listView.adapter = AdapterValuations(valuationsList)
                }
            listView.layoutManager = LinearLayoutManager(this)
            listView.setHasFixedSize(true)

        }

        val retrn = findViewById(R.id.retButton) as Button
        retrn.setOnClickListener {
            val intent = Intent(this@InfoValuations, Buttons_ListCalendar::class.java)
            startActivity(intent)
        }
    }
}

