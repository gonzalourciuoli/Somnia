package com.example.somnia.view
import android.app.AlertDialog
import android.view.LayoutInflater
import com.example.somnia.model.Alarm
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.somnia.R
import com.example.somnia.controller.Controller
import com.google.firebase.firestore.FirebaseFirestore

class AdapterAlarm (var list: MutableList<Alarm>): RecyclerView.Adapter<AdapterAlarm.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.alarm_design, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
        holder.deleteButton.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val layoutInflater : LayoutInflater = LayoutInflater.from(it.context)
            val builder = AlertDialog.Builder(it.context, R.style.Theme_AppCompat_Dialog_Alert)
            builder.setView(layoutInflater.inflate(R.layout.activity_remove, null))
            val dialog: AlertDialog = builder.create()
            var alertView = layoutInflater.inflate(R.layout.activity_remove, null)
            alertView.findViewById<TextView>(R.id.remove_title).text = "Are you sure you want to delete this alarm?"
            alertView.findViewById<TextView>(R.id.remove_description).text= "After you delete it you will be unable to recover the alarm"
            alertView.findViewById<Button>(R.id.delete_valuation_alert_cancel).setOnClickListener {
                dialog.cancel()
            }
            alertView.findViewById<Button>(R.id.delete_valuation_alert_confirm).setOnClickListener {
                val alarm = list.removeAt(position)
                val title = alarm.getTitle()
                val user = alarm.getUserAlarm()
                val hour = alarm.getHour()
                db.collection("Alarms").document(user + "@" + title + "@" + hour).delete()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, list.size)
                Toast.makeText(it.context, "Alarm successfully deleted", Toast.LENGTH_LONG).show()
                dialog.cancel()
            }
            dialog.setView(alertView)
            dialog.show()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val alarmHour: TextView = itemView.findViewById(R.id.alarm_hour)
        val toggleAlarm: Switch = itemView.findViewById(R.id.toggle_alarm)
        val monday: TextView = itemView.findViewById(R.id.monday_view)
        val tuesday: TextView = itemView.findViewById(R.id.tuesday_view)
        val wednesday: TextView = itemView.findViewById(R.id.wednesday_view)
        val thursday: TextView = itemView.findViewById(R.id.thursday_view)
        val friday: TextView = itemView.findViewById(R.id.friday_view)
        val saturday: TextView = itemView.findViewById(R.id.saturday_view)
        val sunday: TextView = itemView.findViewById(R.id.sunday_view)
        val deleteButton = itemView.findViewById<Button>(R.id.deleteAlarm) as Button
        private var controller = Controller()

        fun bindItems(data: Alarm) {

            alarmHour.text = data.getHour()
            if (data.getStatus() == true) {
                toggleAlarm.isChecked = true
            } else {
                toggleAlarm.isChecked = false
            }
            toggleAlarm.setOnClickListener {
                if (data.getStatus() == true) {
                    data.setStatus(false)
                }
            }
            val days: MutableMap<String, Boolean> = data.getWeekDays()
            if (days["Monday"] == true) {
                monday.setTextColor(-16777216)
            }
            if (days["Tuesday"] == true) {
                tuesday.setTextColor(-16777216)
            }
            if (days["Wednesday"] == true) {
                wednesday.setTextColor(-16777216)
            }
            if (days["Thursday"] == true) {
                thursday.setTextColor(-16777216)
            }
            if (days["Friday"] == true) {
                friday.setTextColor(-16777216)
            }
            if (days["Saturday"] == true) {
                saturday.setTextColor(-16777216)
            }
            if (days["Sunday"] == true) {
                sunday.setTextColor(-16777216)
            }

            toggleAlarm.setOnClickListener {
                //controller.changeStatus(data)
            }
        }

    }

}


