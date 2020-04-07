package com.example.somnia.view
import android.view.LayoutInflater
import com.example.somnia.model.Alarm
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.somnia.R
import kotlinx.android.synthetic.main.alarm_design.view.*

class AdapterAlarm (var list: ArrayList<Alarm>):RecyclerView.Adapter<AdapterAlarm.ViewHolder>(){

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){

        fun bindItems(data:Alarm){
            val alarmHour:TextView=itemView.findViewById(R.id.alarm_hour)
            val toggleAlarm:Switch=itemView.findViewById(R.id.toggle_alarm)

            alarmHour.text=data.alarmHour
            if(data.activatedAlarm==true){
                toggleAlarm.isChecked=true
            }
            else{
                toggleAlarm.isChecked=false
            }
            toggleAlarm.setOnClickListener {
                if(data.activatedAlarm==true){
                    data.changeStatus(false)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.alarm_design,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterAlarm.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }


}