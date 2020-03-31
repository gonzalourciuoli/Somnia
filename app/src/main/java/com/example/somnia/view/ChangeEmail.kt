package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.somnia.R
import kotlinx.android.synthetic.main.activity_change_email.*

class ChangeEmail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)
        var current_Email = current_Email.text.toString()
        var password = password.text.toString()
        var new_Email = new_Email.text.toString()

        val save_changes_button = findViewById<Button>(R.id.save_changes) as Button
        save_changes_button.setOnClickListener {
            //Si currentEmail es igual al email del usuario
            //Y si password es igual a la contraseña del usuario
            //Entonces current_Email = new_Email
        }

        val ret_button = findViewById<Button>(R.id.ret) as Button
        ret_button.setOnClickListener {
            val intent = Intent(this@ChangeEmail, Settings::class.java)
            startActivity(intent)
        }
    }

}
