package com.example.somnia.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Settings : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        init()
    }

    private fun init(){
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val chEmail_button = findViewById<TextView>(R.id.ch_Email) as TextView
        chEmail_button.setOnClickListener {
            val intent = Intent(this@Settings, ChangeEmail::class.java)
            startActivity(intent)
        }

        val chPass_button = findViewById<TextView>(R.id.ch_Pass) as TextView
        chPass_button.setOnClickListener {
            val intent = Intent(this@Settings, ChangePassword::class.java)
            startActivity(intent)
        }

        val logout_button = findViewById<TextView>(R.id.log_out) as TextView
        logout_button.setOnClickListener {
            this.logOut()
        }

        val delAcc_button = findViewById<TextView>(R.id.del_Acc) as TextView
        delAcc_button.setOnClickListener {
            this.deleteAccount()
        }
    }

    private fun deleteAccount(){
        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        val builder = AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
        builder.setView(layoutInflater.inflate(R.layout.activity_remove, null))
        val dialog: AlertDialog = builder.create()
        var alertView = layoutInflater.inflate(R.layout.activity_remove, null)
        alertView.findViewById<TextView>(R.id.remove_title).text = "Are you sure you want to delete your account?"
        alertView.findViewById<TextView>(R.id.remove_description).text = "After you delete it you will be unable to recover any data"
        alertView.findViewById<Button>(R.id.delete_valuation_alert_cancel).setOnClickListener {
            dialog.cancel()
        }

        alertView.findViewById<Button>(R.id.delete_valuation_alert_confirm).setOnClickListener {

            db.collection("users").document(user.toString()).delete()
            db.collection("valuations")
                .whereEqualTo("user", user)
                .get().addOnSuccessListener { list ->
                    for (valuation in list){
                        val date = valuation.get("date").toString()
                        db.collection("valuations").document(user.toString() + "@" + date).delete()
                    }
                }
            db.collection("Alarms")
                .whereEqualTo("User", user)
                .get().addOnSuccessListener { list ->
                    for (alarm in list){
                        val title = alarm.get("Title").toString()
                        val hour = alarm.get("Hour").toString()
                        db.collection("Alarms").document(user.toString() + "@" + title + "@" + hour).delete()
                    }
                }

            auth.currentUser?.uid?.let { db.collection("users").document(it).delete()
                .addOnSuccessListener {
                    auth.currentUser!!.delete().addOnSuccessListener {

                        val preferences : SharedPreferences = getSharedPreferences("checkbox", Context.MODE_PRIVATE)
                        val editor2 : SharedPreferences.Editor = preferences.edit()
                        editor2.putString("remember", "false")
                        editor2.apply()
                        finish()
                        Toast.makeText(this, "Account successfuly deleted", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@Settings, logInActivity::class.java)
                        startActivity(intent)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }}
        }
        dialog.setView(alertView)
        dialog.show()
    }

    private fun logOut(){
        val builder = AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
        builder.setView(layoutInflater.inflate(R.layout.activity_remove, null))
        val dialog: AlertDialog = builder.create()
        var alertView = layoutInflater.inflate(R.layout.activity_remove, null)
        alertView.findViewById<TextView>(R.id.remove_title).text = "Are you sure you want to log out?"
        alertView.findViewById<TextView>(R.id.remove_description).text = ""
        alertView.findViewById<ImageView>(R.id.remove_icon).setImageResource(R.drawable.ic_error_outline_black_24dp)
        alertView.findViewById<Button>(R.id.delete_valuation_alert_cancel).setOnClickListener {
            dialog.cancel()
        }
        alertView.findViewById<Button>(R.id.delete_valuation_alert_confirm).setOnClickListener {
            auth.signOut()
            val preferences : SharedPreferences = getSharedPreferences("checkbox", Context.MODE_PRIVATE)
            val editor2 : SharedPreferences.Editor = preferences.edit()
            editor2.putString("remember", "false")
            editor2.apply()
            finish()
            Toast.makeText(this, "Log out", Toast.LENGTH_LONG).show()
            val intent = Intent(this@Settings, logInActivity::class.java)
            startActivity(intent)
        }

        auth.addAuthStateListener {
            if(auth.currentUser == null){
                this.finish()
            }
        }
        dialog.setView(alertView)
        dialog.show()
    }

}