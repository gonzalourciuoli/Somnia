package com.example.somnia.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.somnia.R
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class InfoValuations : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var listView: ListView
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_valuations)

        listView = findViewById(R.id.valuations_list)
        db = FirebaseFirestore.getInstance()
        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        init()
    }

    private fun init() {
        listView.onItemClickListener = this


        val datePreferences = getSharedPreferences("valuations", Context.MODE_PRIVATE)
        val date = datePreferences.getString("date", "")

        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        if (date != "") {
            Toast.makeText(this, "Entra" , Toast.LENGTH_LONG).show()

            db.collection("valuations").document(user.toString()).collection(date.toString())
                .get()
                .addOnSuccessListener { result ->
                    for (d in result){
                        db.collection("valuations").document(user.toString())
                            .collection(date.toString()).document("data")
                            .get().addOnSuccessListener {
                                val date = it.get("date").toString()
                                val numStars = it.get("numStars").toString()
                                val sport_box = it.get("sport_box").toString()
                                val coffee_box = it.get("coffee_box").toString()
                                val alcohol_box = it.get("alcohol_box").toString()
                                val valuation_comment = it.get("valuation_comment").toString()

                                var valuation : String? = ""

                                valuation += (date + ":\n")
                                valuation += ("Rating: " + numStars + " / 5 \n")
                                if (sport_box == "true"){
                                    valuation += ("Sport: Yes \n")
                                }else{
                                    valuation += ("Sport: No \n")
                                }
                                if (coffee_box == "true"){
                                    valuation += ("Coffee: Yes \n")
                                }else{
                                    valuation += ("Coffee: No \n")
                                }
                                if (alcohol_box == "true"){
                                    valuation += ("Alcohol: Yes \n")
                                }else{
                                    valuation += ("Alcohol: No \n")
                                }
                                if (valuation_comment != ""){
                                    valuation += (valuation_comment)
                                }else{
                                    valuation += ("No comments")
                                }

                                arrayAdapter.add(valuation)
                            }
                    }
                }
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

        val retrn = findViewById(R.id.retButton) as Button
        retrn.setOnClickListener {
            val intent = Intent(this@InfoValuations, Buttons_ListCalendar::class.java)
            startActivity(intent)
        }
    }

    private fun deleteValuation(valuation: String){
        try{
            val builder = AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
            builder.setView(layoutInflater.inflate(R.layout.eliminar, null))
            val dialog: AlertDialog = builder.create()
            var alertView = layoutInflater.inflate(R.layout.eliminar, null)
            alertView.findViewById<TextView>(R.id.delete_valuation).text = "Are you sure you want to delete this rating?"
            alertView.findViewById<Button>(R.id.delete_valuation_alert_cancel).setOnClickListener {
                dialog.cancel()
            }
            alertView.findViewById<Button>(R.id.delete_valuation_alert_confirm).setOnClickListener {
                arrayAdapter.remove(valuation)
                listView.adapter = arrayAdapter
                /*db.collection("valuations").document(user.toString()).collection().document("data")
                .delete().addOnSuccessListener {
                    Toast.makeText(this, "Valuation successfully deleted", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error deleting valuation", Toast.LENGTH_LONG).show()
                }*/
                dialog.cancel()
                Toast.makeText(this, "Valuation successfully deleted!" , Toast.LENGTH_LONG).show()
            }
            dialog.setView(alertView)
            dialog.show()

        }catch(e : Exception){
            Toast.makeText(this, e.message , Toast.LENGTH_LONG).show()
            this.finish()
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val builder = AlertDialog.Builder(this)
        val item1 : CharSequence = "Delete"
        val item2 : CharSequence = "Cancel"
        var items : kotlin.Array<CharSequence>? = kotlin.Array(2){ item1 }
        items?.set(1, item2)

        builder.setTitle("Delete valuation")
            .setItems(items, DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                if (i == 0){
                    if (parent != null) {
                        this.deleteValuation(parent.getItemAtPosition(position).toString())
                    }
                }
            })
        val dialog = builder.create()
        dialog.show()
    }
}

