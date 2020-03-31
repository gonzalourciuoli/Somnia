package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.somnia.R
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        var current_Email = current_Email.text.toString()
        var password = password.text.toString()
        var new_Password = new_Password.text.toString()

        val save_changes_button = findViewById<Button>(R.id.save_changes) as Button
        save_changes_button.setOnClickListener {
            //Si currentEmail es igual al email del usuario
            //Y si password es igual a la contraseña del usuario
            //Entonces password = new_Password
        }

        val ret_button = findViewById<Button>(R.id.ret) as Button
        ret_button.setOnClickListener {
            val intent = Intent(this@ChangePassword, Settings::class.java)
            startActivity(intent)
        }
    }
}
