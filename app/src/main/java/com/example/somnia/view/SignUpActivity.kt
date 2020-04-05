package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.signup.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var txtUsername : EditText
    private lateinit var txtEmail : EditText
    private lateinit var txtPassword : EditText
    private lateinit var txtRepeat_password : EditText
    private lateinit var database : FirebaseDatabase
    private lateinit var dbReference : DatabaseReference
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        txtUsername = findViewById(R.id.name)
        txtEmail = findViewById(R.id.email)
        txtPassword = findViewById(R.id.password)
        txtRepeat_password = findViewById(R.id.repeat_password)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbReference = database.reference.child("User")


        val signup_button = findViewById<Button>(R.id.sign_up) as Button
        signup_button.setOnClickListener {
            val accountExit : Int = createNewAccount()
            if (accountExit == 0) {
                val intent = Intent(this@SignUpActivity, Home::class.java)
                startActivity(intent)
            }
        }

        val login_button = findViewById<Button>(R.id.log_in) as Button
        login_button.setOnClickListener {
            val intent = Intent(this@SignUpActivity, Home::class.java)
            startActivity(intent)
        }

    }

    private fun createNewAccount() : Int {
        val username : String = txtUsername.text.toString()
        val email : String = txtEmail.text.toString()
        val password : String = txtPassword.text.toString()
        val repeat_password : String = txtRepeat_password.text.toString()

        if (password != repeat_password) {
            Toast.makeText(this, "The passwords aren't matching", Toast.LENGTH_LONG).show()
            return 1
        } else if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(repeat_password)) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                task ->

                if (task.isComplete) {
                    val user : FirebaseUser? = auth.currentUser
                    verifyEmail(user)

                    val userBD = dbReference.child(user!!.uid)
                    userBD.child("Name").setValue(username)
                }
            }
            return 0
        } else {
            Toast.makeText(this, "Looks like some of the fields are empty", Toast.LENGTH_LONG).show()
            return 2
        }
    }

    private fun verifyEmail(user : FirebaseUser?) {
         user?.sendEmailVerification()?.addOnCompleteListener(this) {
             task ->

             if (task.isComplete) {
                 Toast.makeText(this, "Email sent", Toast.LENGTH_LONG).show()
             } else {
                 Toast.makeText(this, "An error occurred trying to send the email", Toast.LENGTH_LONG).show()

             }
         }
    }
}