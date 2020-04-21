package com.example.somnia.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.somnia.R
import java.lang.reflect.Array
import java.util.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

class InfoValuations : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_valuations)

        listView = findViewById(R.id.valuations_list) as ListView
        db = FirebaseFirestore.getInstance()
        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.list_content)

        init()

        /*val bundle: Bundle
        val day: Int
        val month: Int
        val year: Int
        bundle = intent.extras!!
        day = bundle.getInt("day")
        month = bundle.getInt("month")
        year = bundle.getInt("year")
        val cadena: String = day.toString() + "/" + month.toString() + "/" + year.toString()*/
    }

    private fun init() {
        //listView.setOnLongClickListener(this)

        if (db.collection("valuations") != null){
            /*db.collection("valuations").document("2020").collection("3").document("20")
                    .get().addOnSuccessListener {
                        val date = it.get("date").toString()
                        val numStars = it.get("numStars").toString()
                        val sport_box = it.get("sport_box").toString()
                        val coffee_box = it.get("coffee_box").toString()
                        val alcohol_box = it.get("alcohol_box").toString()
                        val valutaion_comment = it.get("valuation_comment").toString()

                        arrayAdapter.add("Date: " + date +"\n")
                        arrayAdapter.add("Rating: " + numStars + "/5 \n")
                        if (sport_box == "true"){
                            arrayAdapter.add("Sport \n")
                        }
                        if (coffee_box == "true"){
                            arrayAdapter.add("Coffee \n")
                        }
                        if (alcohol_box == "true"){
                            arrayAdapter.add("Alcohol \n")
                        }
                        if (valutaion_comment != ""){
                            arrayAdapter.add(valutaion_comment)
                        }else{
                            arrayAdapter.add("No comments")
                        }
                        listView.adapter = arrayAdapter
                    }*/

            db.collection("valuations")
                .get()
                .addOnSuccessListener { result ->
                    for (valuation in result){
                        Log.d("TAG", "$valuation.id) => $(valuation.data)")
                        val date = valuation.id
                        val numStars = valuation.data.get("numStars").toString()
                        val sport_box = valuation.data.get("sport_box").toString()
                        val coffee_box = valuation.data.get("coffee_box").toString()
                        val alcohol_box = valuation.data.get("alcohol_box").toString()
                        val valuataion_comment = valuation.data.get("valuation_comment").toString()

                        arrayAdapter.add("Date: " + date +"\n")
                        arrayAdapter.add("Rating: " + numStars + "/5 \n")
                        if (sport_box == "true"){
                            arrayAdapter.add("Sport \n")
                        }
                        if (coffee_box == "true"){
                            arrayAdapter.add("Coffee \n")
                        }
                        if (alcohol_box == "true"){
                            arrayAdapter.add("Alcohol \n")
                        }
                        if (valuataion_comment != ""){
                            arrayAdapter.add(valuataion_comment)
                        }else{
                            arrayAdapter.add("No comments")
                        }
                        listView.adapter = arrayAdapter
                    }
                    Toast.makeText(this, "entra", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting valuation. ", exception)
                }
        }else{
            Toast.makeText(this, "There's no valuations" , Toast.LENGTH_LONG).show()
        }


        val retrn = findViewById<Button>(R.id.retButton) as Button
        retrn.setOnClickListener {
            val intent = Intent(this@InfoValuations, Buttons_ListCalendar::class.java)
            startActivity(intent)
        }
    }

    /*override fun onLongClick(v: View?): Boolean {
        val builder = AlertDialog.Builder(this)
        val item1 : CharSequence = "Delate"
        val item2 : CharSequence = "Cancel"
        var items : kotlin.Array<CharSequence>? = null
        items?.set(0, item1)
        items?.set(1, item2)

        builder.setTitle("Delate valuation")
            .setItems(items, DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                if (i == 0){

                } else{
                    val intent = Intent(this@InfoValuations, InfoValuations::class.java)
                    startActivity(intent)
                }

            })

        val dialog = builder.create()
        dialog.show()
        return false
    }*/

    private fun delate(valuation: String){
        try{
            arrayAdapter.remove("date")
            listView.adapter = arrayAdapter

        }catch(e : Exception){
            Toast.makeText(this, e.message , Toast.LENGTH_LONG).show()
            this.finish()
        }

    }
}

