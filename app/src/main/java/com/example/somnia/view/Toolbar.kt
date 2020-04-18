package com.example.somnia.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.somnia.view.AlarmsActivity
import com.example.somnia.R
import kotlinx.android.synthetic.main.toolbar.view.*

class Toolbar : androidx.fragment.app.Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewActual = inflater.inflate(R.layout.toolbar, container, false)
        viewActual.button_home.setOnClickListener { view ->
            if (activity?.javaClass?.simpleName != Home::class.simpleName) {
                val principal = Intent(activity, Home::class.java)
                startActivity(principal)
            }
        }

        viewActual.button_graph.setOnClickListener { view ->
            if (activity?.javaClass?.simpleName != ChartsCard::class.simpleName) {
                val principal = Intent(activity, ChartsCard::class.java)
                startActivity(principal)
            }
        }

        viewActual.button_clock.setOnClickListener { view ->
            if (activity?.javaClass?.simpleName != AlarmsActivity::class.simpleName) {
                val principal = Intent(activity, AlarmsActivity::class.java)
                startActivity(principal)
            }
        }

        viewActual.button_calculator.setOnClickListener { view ->
            if (activity?.javaClass?.simpleName != Calculator::class.simpleName) {
                val principal = Intent(activity, Calculator::class.java)
                startActivity(principal)
            }
        }

        viewActual.button_settings.setOnClickListener { view ->
            if (activity?.javaClass?.simpleName != Settings::class.simpleName) {
                val principal = Intent(activity, Settings::class.java)
                startActivity(principal)
            }
        }
        return viewActual
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
