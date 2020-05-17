package com.example.somnia.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.somnia.R
import android.content.Context

class Welcome: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        createNotificationChannel()

        val constraint: ConstraintLayout = findViewById(R.id.animated_layout)
        val animationDrawable: AnimationDrawable = constraint.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        val begin = findViewById<Button>(R.id.begin) as Button
        begin.setOnClickListener {
            val intent = Intent(this@Welcome, logInActivity::class.java)
            startActivity(intent)
        }

    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Alarms"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("Alarms", name, importance)
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}