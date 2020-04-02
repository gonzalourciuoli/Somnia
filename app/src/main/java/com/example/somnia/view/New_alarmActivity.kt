package com.example.somnia.view
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.somnia.R
import kotlinx.android.synthetic.main.alarm_design.*
import kotlinx.android.synthetic.main.alarms.*

class New_alarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_alarm)

        val add_button = findViewById<Button>(R.id.add)
        add_button.setOnClickListener {
            val intent = Intent(this@New_alarmActivity, AlarmsActivity::class.java)
            startActivity(intent)
        }

        val cancel_button = findViewById<Button>(R.id.cancel)
        cancel_button.setOnClickListener {
            val intent = Intent(this@New_alarmActivity, AlarmsActivity::class.java)
            startActivity(intent)
        }

        val parentLayout = findViewById<ScrollView>(R.id.scrollView_alarms)
        View.inflate(this, R.layout.alarm_design, parentLayout)
    }
}