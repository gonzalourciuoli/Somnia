package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.somnia.R
import com.example.somnia.controller.Controller
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private lateinit var txtUsername : EditText
    private lateinit var txtEmail : EditText
    private lateinit var txtPassword : EditText
    private lateinit var txtRepeat_password : EditText
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth
    private val controller = Controller()
    private val hasher = Hasher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

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
        } else if (password.length < 6){
            Toast.makeText(this, "The password must be 6 characters minimum", Toast.LENGTH_LONG).show()
        } else if (!validarEmail(email)){
            Toast.makeText(this, "This email is invalid", Toast.LENGTH_LONG).show()
        } else if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(repeat_password)) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                    task ->

                if (task.isComplete) {
                    db.collection("users").document(email)
                        .get().addOnSuccessListener {
                            val user = it.get("email").toString()
                            if (email == user){
                                Toast.makeText(this, "This account already exists", Toast.LENGTH_LONG).show()
                            }else{
                                val user = hashMapOf(
                                    "username" to username,
                                    "email" to email,
                                    "password" to hasher.hash(password)
                                )
                                db.collection("users").document(email).set(user)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "User created", Toast.LENGTH_LONG).show()
                                        startActivity(Intent(this, logInActivity::class.java))
                                        var user = auth.currentUser?.sendEmailVerification()?.addOnCompleteListener(this) { task ->
                                            if (task.isComplete) {
                                                Toast.makeText(this, "Verify your email", Toast.LENGTH_LONG).show()
                                            } else {
                                                Toast.makeText(this,"An error occurred trying to send the email verification",Toast.LENGTH_LONG).show()
                                            }
                                        }

                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(this, "User creation failed", Toast.LENGTH_LONG).show()
                                    }
                            }

                        }
                }
            }
        } else {
            Toast.makeText(this, "Looks like some of the fields are empty", Toast.LENGTH_LONG).show()
        }
    }

    private fun validarEmail(email: String): Boolean{
        val pattern : Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()

    }
}