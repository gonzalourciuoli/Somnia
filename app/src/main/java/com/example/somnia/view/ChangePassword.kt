package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.somnia.controller.Controller

class ChangePassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
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
        controller = Controller()

        val save_changes_button = findViewById<Button>(R.id.save_changes) as Button
        save_changes_button.setOnClickListener {
            var email = email.text.toString()
            var password = password.text.toString()
            var password_new = new_password.text.toString()
            if (password !=password_new){
                controller.changePassword(password_new)
            }
            val intent = Intent(this@ChangePassword, Settings::class.java)
            startActivity(intent)
        }

        val ret_button = findViewById<Button>(R.id.ret) as Button
        ret_button.setOnClickListener {
            val intent = Intent(this@ChangePassword, Settings::class.java)
            startActivity(intent)
        }

    }
}
