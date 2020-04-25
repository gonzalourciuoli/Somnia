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
    private lateinit var email: TextView
    private lateinit var password: TextView
    private lateinit var new_password: TextView
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        email = findViewById(R.id.current_Email)
        password = findViewById(R.id.password)
        new_password = findViewById(R.id.new_Password)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userCollection = db.collection("users")
        controller = Controller()
    }

        fun save_changes(view: View) {
            changePassword()
            startActivity(Intent(this, Settings::class.java))
        }

        fun ret(view: View) {
            startActivity(Intent(this, Settings::class.java))
        }

        private fun changePassword() {
            val current_Email : String = current_Email.text.toString()
            val password : String = password.text.toString()
            val new_password : String = new_Password.text.toString()

            if(!TextUtils.isEmpty(current_Email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(new_password)) {
                auth.signInWithEmailAndPassword(current_Email, password).addOnCompleteListener {
                        task ->

                    if (task.isComplete) {
                        auth.currentUser?.updatePassword(new_password)
                        userCollection
                            .document(current_Email).update("password", new_password)

                    } else {
                        Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Looks like some of the fields are empty", Toast.LENGTH_LONG).show()
            }
        }

    }

