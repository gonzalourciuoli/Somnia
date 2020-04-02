package com.example.somnia.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.somnia.R
import android.view.View
import android.view.ViewGroup
import android.widget.*


class AlarmsActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarms)

        var listView : ListView = findViewById(R.id.alarms_list)
        var list = mutableListOf<PresentationAlarm>()

        list.add(PresentationAlarm("6:30"))
        list.add(PresentationAlarm("7:00"))
        list.add(PresentationAlarm("9:10"))
        list.add(PresentationAlarm("12:05"))
        list.add(PresentationAlarm("13:15"))

        listView.adapter = MyAdapter(this, R.layout.alarm_design,list)
        /**listView.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->
        }*/

        val add_button = findViewById<Button>(R.id.add_button)
        add_button.setOnClickListener {
            val intent = Intent(this@AlarmsActivity, New_alarmActivity::class.java)
            startActivity(intent)
        }
        

    }

}
