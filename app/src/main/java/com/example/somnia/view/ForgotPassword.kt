package com.example.somnia.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*

class ForgotPassword: AppCompatActivity()  {

    private lateinit var txtEmailAccount : EditText
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var userCollection : CollectionReference

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        txtEmailAccount = findViewById(R.id.email_account)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userCollection = db.collection("users")

        val send = findViewById<Button>(R.id.send) as Button
        send.setOnClickListener {
            sendCode()
        }

        val skip = findViewById<Button>(R.id.skip) as Button
        skip.setOnClickListener {
            val intent = Intent(this@ForgotPassword, logInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun sendCode(){
        val emailAccount : String = txtEmailAccount.text.toString()

        if(TextUtils.isEmpty(emailAccount))
            Toast.makeText(this, "The field is empty. Type your email" , Toast.LENGTH_SHORT ).show()
        else{
            auth.fetchSignInMethodsForEmail(emailAccount)
                .addOnCompleteListener(this){
                        task ->
                    if (task.isSuccessful) {
                        auth.sendPasswordResetEmail(emailAccount)
                            .addOnCompleteListener(this) {
                                    task ->
                                if (task.isSuccessful)
                                    Toast.makeText(
                                        this,
                                        "Code has been send to your email",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                else
                                    Toast.makeText(
                                        this,
                                        "Fail to send the code",
                                        Toast.LENGTH_SHORT
                                    ).show()
                            }

                    }else
                        Toast.makeText(this, "Not registered account to this email" , Toast.LENGTH_SHORT ).show()
                }

        }

    }
}