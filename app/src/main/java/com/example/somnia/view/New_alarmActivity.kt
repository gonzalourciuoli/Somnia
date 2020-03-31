package com.example.somnia.view
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.somnia.R

class New_alarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_alarm)

        val add_button = findViewById<Button>(R.id.add) as Button
        add_button.setOnClickListener {

        }

        val cancel_button = findViewById<Button>(R.id.cancel) as Button
        cancel_button.setOnClickListener {
            val intent = Intent(this@New_alarmActivity, AlarmsActivity::class.java)
            startActivity(intent)
        }
    }




}