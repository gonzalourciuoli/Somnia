package com.example.somnia.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

//import com.google.firebase.database.FirebaseDatabase

class logInActivity : AppCompatActivity() {

    private lateinit var txtUsername : EditText
    private lateinit var txtPassword : EditText
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        txtUsername = findViewById(R.id.user)
        txtPassword = findViewById(R.id.password)

        auth = FirebaseAuth.getInstance()

        val forgot = findViewById<Button>(R.id.forgot_password) as Button
        forgot.setOnClickListener {
            val intent = Intent(this@logInActivity, ForgotPassword::class.java)
            startActivity(intent)
        }

    }

    fun login(view : View) {
        loginUser(view)
    }

    fun sign_up(view : View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    fun forgot_password(view : View) {
        startActivity(Intent(this, ForgotPassword::class.java))
    }

    private fun loginUser(view : View) {
        val username : String = txtUsername.text.toString()
        val password : String = txtPassword.text.toString()

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {

            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this) {
                task ->

                if (task.isSuccessful) {

                    val userPreferences = view.context.getSharedPreferences("users", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = userPreferences.edit()

                    editor.putString("email", username)
                    editor.apply()

                    startActivity(Intent(this, Home::class.java))
                    Toast.makeText(this, "You logged into your account", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}