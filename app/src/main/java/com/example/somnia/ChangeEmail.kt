package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.somnia.R
import kotlinx.android.synthetic.main.change_email.*

class ChangeEmail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_email)
        var currentEmail = currentEmail.text.toString()
        var password = password.text.toString()
        var newEmail = newEmail.text.toString()

        val savechanges_button = findViewById<Button>(R.id.savechanges) as Button
        savechanges.setOnClickListener {
            //Si currentEmail es igual al email del usuario
            //Y si password es igual a la contraseña del usuario
            //Entonces currentEmail = newEmail
        }

        val ret_button = findViewById<Button>(R.id.ret) as Button
        ret.setOnClickListener {
            val intent = Intent(this@ChangeEmail, Settings::class.java)
            startActivity(intent)
        }
    }

}
