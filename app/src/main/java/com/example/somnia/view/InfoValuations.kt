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
import com.example.somnia.controller.Controller
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class InfoValuations : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var listView: ListView
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var db: FirebaseFirestore
    private  var controller = Controller()

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

        if (user != "") {
            var list = controller.listViewValuations(user.toString())
            Toast.makeText(this, list.toString() , Toast.LENGTH_LONG).show()

            for (item in list){
                arrayAdapter.add(item)
            }
        }

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
            builder.setView(layoutInflater.inflate(R.layout.activity_remove, null))
            val dialog: AlertDialog = builder.create()
            var alertView = layoutInflater.inflate(R.layout.activity_remove, null)
            alertView.findViewById<TextView>(R.id.remove_title).text = "Are you sure you want to delete this rating?"
            alertView.findViewById<TextView>(R.id.remove_description).text= "After you delete it you will be unable to recover the rating"
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
        var items : Array<CharSequence>? = Array(2){ item1 }
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

