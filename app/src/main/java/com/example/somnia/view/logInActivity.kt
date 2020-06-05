package com.example.somnia.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth

//import com.google.firebase.database.FirebaseDatabase

class logInActivity : AppCompatActivity() {

    private lateinit var txtUsername : EditText
    private lateinit var txtPassword : EditText
    private lateinit var auth : FirebaseAuth
    private lateinit var rememberme: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        txtUsername = findViewById(R.id.user)
        txtPassword = findViewById(R.id.password)
        rememberme = findViewById(R.id.rememberme)

        auth = FirebaseAuth.getInstance()

        val preferences : SharedPreferences = getSharedPreferences("checkbox", Context.MODE_PRIVATE)
        var chbx : String? = preferences.getString("remember", "")

        if (chbx == "true"){
            startActivity(Intent(this, Home::class.java))
        }

        val forgot = findViewById<Button>(R.id.forgot_password) as Button
        forgot.setOnClickListener {
            val intent = Intent(this@logInActivity, ForgotPassword::class.java)
            startActivity(intent)
        }

        rememberme.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked){
                val preferences : SharedPreferences = getSharedPreferences("checkbox", Context.MODE_PRIVATE)
                val editor2 : SharedPreferences.Editor = preferences.edit()
                editor2.putString("remember", "true")
                editor2.apply()
            } else{
                val preferences : SharedPreferences = getSharedPreferences("checkbox", Context.MODE_PRIVATE)
                val editor2 : SharedPreferences.Editor = preferences.edit()
                editor2.putString("remember", "false")
                editor2.apply()
            }
        }

    }

    fun login(view : View) {
        loginUser(view)
    }

    fun sign_up(view : View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    /*fun forgot_password(view : View) {
        startActivity(Intent(this, ForgotPassword::class.java))
    }*/

    private fun loginUser(view : View) {
        val username : String = txtUsername.text.toString()
        val password : String = txtPassword.text.toString()

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Looks like some of the fields are empty", Toast.LENGTH_LONG)
                .show()
        } else {
            auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this) {
                task ->
                if (task.isSuccessful) {
                    if (auth.currentUser?.isEmailVerified!!){
                        val userPreferences = view.context.getSharedPreferences("users", Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = userPreferences.edit()

                        editor.putString("email", username)
                        editor.apply()

                        startActivity(Intent(this, Home::class.java))
                        Toast.makeText(this, "You logged into your account", Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(this, "You need to verify your email first", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}