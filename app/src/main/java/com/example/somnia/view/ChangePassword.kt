package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.somnia.controller.Controller
import com.google.firebase.firestore.CollectionReference
import kotlinx.android.synthetic.main.activity_change_email.*
import kotlinx.android.synthetic.main.activity_change_email.current_Email
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var userCollection : CollectionReference
    private lateinit var password: TextView
    private lateinit var new_password: TextView
    private lateinit var repeat_new_password: TextView
    private lateinit var controller: Controller
    private val hasher = Hasher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        password = findViewById(R.id.current_password)
        new_password = findViewById(R.id.new_password)
        repeat_new_password = findViewById(R.id.repeat_new_Password)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userCollection = db.collection("users")
        controller = Controller()
    }

        fun save_changes(view: View) {
            changePassword()
        }

        fun ret(view: View) {
            startActivity(Intent(this, Settings::class.java))
        }

        private fun changePassword() {
            val password : String = password.text.toString()
            val new_password : String = new_password.text.toString()
            val repeat_new_password : String = repeat_new_password.text.toString()

            if(TextUtils.isEmpty(repeat_new_password) || TextUtils.isEmpty(password) || TextUtils.isEmpty(new_password)) {
                Toast.makeText(this, "Looks like some of the fields are empty", Toast.LENGTH_LONG).show()
            }else if(password == new_password){
                Toast.makeText(this, "This is not a new password", Toast.LENGTH_LONG)
                    .show()
            }else if(new_password != repeat_new_password) {
                Toast.makeText(this, "New password fields aren't matching", Toast.LENGTH_LONG)
                    .show()
            }else if (new_password.length<6) {
                Toast.makeText(this, "The password must be 6 characters minimum", Toast.LENGTH_LONG)
                    .show()
            }else{
                auth.signInWithEmailAndPassword(auth.getCurrentUser()?.getEmail().toString(), password
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        auth.currentUser?.updatePassword(new_password)
                        userCollection
                            .document(auth.getCurrentUser()?.getEmail().toString())
                            .update("password", hasher.hash(new_password))

                        startActivity(Intent(this, Settings::class.java))
                        Toast.makeText(this, "Password has been updated", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


