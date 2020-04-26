package com.example.somnia.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.somnia.R
import com.example.somnia.model.Valuation

class AdapterValuations(var list: MutableList<Valuation>): RecyclerView.Adapter<AdapterValuations.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.valuation_design, null)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val date = itemView.findViewById<TextView>(R.id.date_rating)
        val numStars = itemView.findViewById<TextView>(R.id.numStars_rating)
        val sport_box = itemView.findViewById<TextView>(R.id.sport_rating)
        val coffee_box = itemView.findViewById<TextView>(R.id.coffee_rating)
        val alcohol_box = itemView.findViewById<TextView>(R.id.alcohol_rating)
        val valuation_comment = itemView.findViewById<TextView>(R.id.comment_rating)

        fun bindItems(valuation: Valuation){
            date.text = valuation.getDateValuation()
            numStars.text = "Rating: " + valuation.getNumStarsValuation() + " / 5"
            if (valuation.getSportBoxValuation() == "true"){
                sport_box.text = "Sport: Yes"
            }else{
                sport_box.text = "Sport: No"
            }
            if (valuation.getCoffeeBoxValuation() == "true"){
                coffee_box.text = "Coffee: Yes"
            }else{
                coffee_box.text = "Coffee: No"
            }
            if (valuation.getAlcoholBoxValuation() == "true"){
                alcohol_box.text = "Alcohol: Yes"
            }else{
                alcohol_box.text = "Alcohol: No"
            }
            if (valuation.getValuationComment() == ""){
                valuation_comment.text = "No comments"
            } else{
                valuation_comment.text = valuation.getValuationComment()
            }
        }
    }
}