package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

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

    }

    fun login(view : View) {
        loginUser()
    }

    fun sign_up(view : View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    fun forgot_password(view : View) {

    }

    private fun loginUser() {
        val username : String = txtUsername.text.toString()
        val password : String = txtPassword.text.toString()

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this) {
                task ->

                if (task.isSuccessful) {
                    startActivity(Intent(this, Home::class.java))
                } else {
                    Toast.makeText(this, "Error en la autenticación", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}