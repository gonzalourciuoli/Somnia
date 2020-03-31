package com.example.somnia
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.alarm_design.*
import kotlinx.android.synthetic.main.alarms.*

class New_alarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_alarm)

        val parentLayout = findViewById<ScrollView>(R.id.scrollView_alarms)
        View.inflate(this, R.layout.alarm_design, parentLayout)

        val monday_switch_act = findViewById<Switch>(R.id.monday_switch)
        monday_switch_act.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                monday_view.setTextColor(Color.parseColor("#6085e0"))
            } else {
                monday_view.setTextColor(Color.parseColor("#000000"))
            }
        }

        val tuesday_switch_act = findViewById<Switch>(R.id.tuesday_switch)
        tuesday_switch_act.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                tuesday_view.setTextColor(Color.parseColor("#6085e0"))
            } else {
                tuesday_view.setTextColor(Color.parseColor("#000000"))
            }
        }

        val wednesday_switch_act = findViewById<Switch>(R.id.wednesday_switch)
        wednesday_switch_act.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                wednesday_view.setTextColor(Color.parseColor("#6085e0"))
            } else {
                wednesday_view.setTextColor(Color.parseColor("#000000"))
            }
        }

        val thursday_switch_act = findViewById<Switch>(R.id.thursday_switch)
        thursday_switch_act.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                thursday_view.setTextColor(Color.parseColor("#6085e0"))
            } else {
                thursday_view.setTextColor(Color.parseColor("#000000"))
            }
        }

        val friday_switch_act = findViewById<Switch>(R.id.friday_switch)
        friday_switch_act.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                friday_view.setTextColor(Color.parseColor("#6085e0"))
            } else {
                friday_view.setTextColor(Color.parseColor("#000000"))
            }
        }

        val saturday_switch_act = findViewById<Switch>(R.id.saturday_switch)
        saturday_switch_act.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                saturday_view.setTextColor(Color.parseColor("#6085e0"))
            } else {
                saturday_view.setTextColor(Color.parseColor("#000000"))
            }
        }

        val sunday_switch_act = findViewById<Switch>(R.id.sunday_switch)
        sunday_switch_act.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sunday_view.setTextColor(Color.parseColor("#6085e0"))
            } else {
                sunday_view.setTextColor(Color.parseColor("#000000"))
            }
        }

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