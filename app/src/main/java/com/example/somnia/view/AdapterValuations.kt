package com.example.somnia.view

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.somnia.R
import com.example.somnia.model.Valuation
import com.google.firebase.firestore.FirebaseFirestore

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

        holder.deleteButton.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val layoutInflater : LayoutInflater = LayoutInflater.from(it.context)
            val builder = AlertDialog.Builder(it.context, R.style.Theme_AppCompat_Dialog_Alert)
            builder.setView(layoutInflater.inflate(R.layout.activity_remove, null))
            val dialog: AlertDialog = builder.create()
            var alertView = layoutInflater.inflate(R.layout.activity_remove, null)
            alertView.findViewById<TextView>(R.id.remove_title).text = "Are you sure you want to delete this rating?"
            alertView.findViewById<TextView>(R.id.remove_description).text= "After you delete it you will be unable to recover the rating"
            alertView.findViewById<Button>(R.id.delete_valuation_alert_cancel).setOnClickListener {
                dialog.cancel()
            }
            alertView.findViewById<Button>(R.id.delete_valuation_alert_confirm).setOnClickListener {
                val valuation = list.removeAt(position)
                val date = valuation.getDateValuation()
                val user = valuation.getUserValuation()
                db.collection("valuations").document(user + "@" + date).delete()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, list.size)
                Toast.makeText(it.context, "Valuation successfully deleted", Toast.LENGTH_LONG).show()
                dialog.cancel()
            }
            dialog.setView(alertView)
            dialog.show()
        }
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val date = itemView.findViewById<TextView>(R.id.date_rating)
        val numStars = itemView.findViewById<TextView>(R.id.numStars_rating)
        val sport_box = itemView.findViewById<TextView>(R.id.sport_rating)
        val coffee_box = itemView.findViewById<TextView>(R.id.coffee_rating)
        val alcohol_box = itemView.findViewById<TextView>(R.id.alcohol_rating)
        val valuation_comment = itemView.findViewById<TextView>(R.id.comment_rating)
        val deleteButton = itemView.findViewById<Button>(R.id.deleteImage) as Button

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