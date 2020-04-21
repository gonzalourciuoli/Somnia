package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_change_email.*

class ChangeEmail : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)
    }

    fun save_changes(view: View) {
        changeEmail()
    }

    fun ret(view: View) {
        startActivity(Intent(this, Settings::class.java))
    }

    private fun changeEmail() {
        val current_Email : String = current_Email.text.toString()
        val password : String = password.text.toString()
        val new_Email : String = new_Email.text.toString()
    }

}
