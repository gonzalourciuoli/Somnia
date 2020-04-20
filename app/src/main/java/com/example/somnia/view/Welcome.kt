package com.example.somnia.view

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.somnia.R

class Welcome: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

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
}