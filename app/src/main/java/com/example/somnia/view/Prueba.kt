package com.example.somnia.view
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.somnia.R

public class Prueba: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act)

        var mainGrid : GridLayout = findViewById(R.id.grid)
        setToggleEvent(mainGrid)
    }

    fun setToggleEvent(mainGrid: GridLayout){
        for(i in 1..mainGrid.childCount){
            val cardView: CardView = mainGrid.getChildAt(i) as CardView
            cardView.setOnClickListener {
                fun onClick(view: View){
                    if (i == 1){
                        val intent = Intent(this@Prueba, BarChart::class.java)
                        startActivity(intent) }
                    if (i == 2){
                        val intent = Intent(this@Prueba, HorizonBarChart::class.java)
                        startActivity(intent) }
                    if (i == 3){
                        val intent = Intent(this@Prueba, PieChart::class.java)
                        startActivity(intent) }
                    if (i == 4){
                        val intent = Intent(this@Prueba, LinearChart::class.java)
                        startActivity(intent) }
                }
            }
        }


    }

}