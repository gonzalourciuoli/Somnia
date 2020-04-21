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
import com.example.somnia.R
import java.lang.reflect.Array
import java.util.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

class InfoValuations : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var listView: ListView
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_valuations)

        listView = findViewById(R.id.valuations_list) as ListView
        db = FirebaseFirestore.getInstance()
        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

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
        listView.onItemClickListener = this


        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        if (user != "") {
            //EL TEU CODI AQUI
        }


        db.collection("valuations").document(user.toString()).collection("data")
            .document("data").get().addOnSuccessListener {
                val numStars : String? = it.getString("numStars")
                arrayAdapter.add(numStars!!)
            }
        /*if (db.collection("valuations") != null){
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
        }*/

        arrayAdapter.add("valuation 1")
        arrayAdapter.add("valuation 2")
        arrayAdapter.add("valuation 3")
        listView.adapter = arrayAdapter

        val retrn = findViewById<Button>(R.id.retButton) as Button
        retrn.setOnClickListener {
            val intent = Intent(this@InfoValuations, Buttons_ListCalendar::class.java)
            startActivity(intent)
        }
    }

    private fun deleteValuation(valuation: String){
        try{
            arrayAdapter.remove(valuation)
            listView.adapter = arrayAdapter
            /*db.collection("valuations").document(valuation)
                .delete().addOnSuccessListener {
                    Toast.makeText(this, "Valuation successfully deleted", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error deleting valuation", Toast.LENGTH_LONG).show()
                }*/
            Log.d("TAG", "Valuation successfully deleted!")

        }catch(e : Exception){
            Toast.makeText(this, e.message , Toast.LENGTH_LONG).show()
            this.finish()
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val builder = AlertDialog.Builder(this)
        val item1 : CharSequence = "Delete"
        val item2 : CharSequence = "Cancel"
        var items : kotlin.Array<CharSequence>? = null
        items?.set(0, item1)
        items?.set(1, item2)

        builder.setTitle("Delete valuation")
            .setItems(items, DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                if (i == 0){
                    if (parent != null) {
                        this.deleteValuation(parent.getItemAtPosition(position).toString())
                    }

                } else{
                    val intent = Intent(this@InfoValuations, InfoValuations::class.java)
                    startActivity(intent)
                }
            })

        val dialog = builder.create()
        dialog.show()
    }
}

