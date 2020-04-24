package com.example.somnia.view
import android.view.LayoutInflater
import com.example.somnia.model.Alarm
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.somnia.R

class AdapterAlarm (var list: MutableList<Alarm>): RecyclerView.Adapter<AdapterAlarm.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.prova, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
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
        }

    }

}


