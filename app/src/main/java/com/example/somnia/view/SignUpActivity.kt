package com.example.somnia.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var txtUsername : EditText
    private lateinit var txtEmail : EditText
    private lateinit var txtPassword : EditText
    private lateinit var txtRepeat_password : EditText
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        txtUsername = findViewById(R.id.name)
        txtEmail = findViewById(R.id.email)
        txtPassword = findViewById(R.id.password)
        txtRepeat_password = findViewById(R.id.repeat_password)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

    }

    fun sign_up(view: View) {
        createNewAccount()
    }

    fun login(view: View) {
        startActivity(Intent(this, logInActivity::class.java))
    }

    private fun createNewAccount() {
        val username: String = txtUsername.text.toString()
        val email: String = txtEmail.text.toString()
        val password: String = txtPassword.text.toString()
        val repeat_password: String = txtRepeat_password.text.toString()

        if (password != repeat_password) {
            Toast.makeText(this, "The passwords aren't matching", Toast.LENGTH_LONG).show()
        } else if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(repeat_password)) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                    task ->

                if (task.isComplete) {
                    //existingUser(username, email, password)
                    val user = hashMapOf(
                        "username" to username,
                        "email" to email,
                        "password" to password
                    )
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "User created", Toast.LENGTH_LONG)
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "User creation failed", Toast.LENGTH_LONG)
                        }

                    startActivity(Intent(this, logInActivity::class.java))
                }
            }
        } else {
            Toast.makeText(this, "Looks like some of the fields are empty", Toast.LENGTH_LONG).show()
        }
    }

    private fun verifyEmail(user: FirebaseUser?) {
        user?.sendEmailVerification()?.addOnCompleteListener(this) { task ->

            if (task.isComplete) {
                Toast.makeText(this, "Email sent", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    this,
                    "An error occurred trying to send the email",
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }

    /*private fun existingUser(username : String, email : String, password : String){
        db.collection("users").document(email)
            .get().addOnSuccessListener {
                Toast.makeText(this, "This account already exists", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                db.collection("users").document(email).set(mapOf(
                    "username" to username,
                    "email" to email,
                    "password" to password
                ))
                Toast.makeText(this, "User created", Toast.LENGTH_LONG)
            }
    }*/
}