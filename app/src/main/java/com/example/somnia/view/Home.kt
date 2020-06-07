package com.example.somnia.view

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.somnia.R
import com.example.somnia.controller.Controller
import com.example.somnia.model.Alarm
import com.example.somnia.model.AlarmReceiver
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.*
import kotlinx.android.synthetic.main.new_alarm.*
import java.util.Calendar

class Home : AppCompatActivity() {
    private lateinit var am: AlarmManager
    private lateinit var cont: Context
    private lateinit var pi: PendingIntent
    private lateinit var alarm: Alarm
    private lateinit var db: FirebaseFirestore

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = FirebaseFirestore.getInstance()

        val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
        val user = userPreferences.getString("email", "")

        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        this.cont = this
        var intent_reciver: Intent = Intent(this, AlarmReceiver::class.java)
        var calendar: Calendar = Calendar.getInstance()

        setContentView(R.layout.activity_home)
        val current = LocalDateTime.now()
        val data : String = current.year.toString() + "-" + current.monthValue.toString() + "-" + current.dayOfMonth.toString()

        db.collection("pie_chart").document(user.toString() + "@" + data)
            .get().addOnSuccessListener {
                val rem = it.get("REM sleep").toString()
                if (rem == "null"){
                    val builder = AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                    builder.setView(layoutInflater.inflate(R.layout.activity_horas_dormidas, null))
                    val dialog: AlertDialog = builder.create()
                    var alertView = layoutInflater.inflate(R.layout.activity_horas_dormidas, null)
                    alertView.findViewById<TextView>(R.id.horas_dormidas_title).text = "How many hours did you sleep tonight?"
                    alertView.findViewById<ImageView>(R.id.horas_dormidas).setImageResource(R.drawable.ic_horas_dormidas)

                    alertView.findViewById<Button>(R.id.horas_dormidas_alert_confirm).setOnClickListener {
                        var horas : EditText = alertView.findViewById(R.id.num_horas_dormidas)
                        val horas_dormidas = horas.text.toString()
                        var horas_totales : Float
                        if (horas_dormidas.length > 2){
                            var horas_horas : Int = (horas_dormidas.substringBefore(":")).toInt()
                            var horas_minutos : Int = (horas_dormidas.substringAfter(":")).toInt()

                            horas_totales  = (horas_horas + (horas_minutos * 0.01)).toFloat()
                        } else{
                            horas_totales = horas_dormidas.toFloat()
                        }

                        db.collection("pie_chart").document(user.toString() + "@" + data).set(mapOf(
                            "Deep sleep" to (horas_totales * 0.2),
                            "Light sleep" to (horas_totales * 0.5),
                            "REM sleep" to (horas_totales * 0.3),
                            "user" to user.toString()
                        ))

                        var semana : Int
                        if (current.dayOfMonth <= 7){
                            semana = 1
                        } else if (current.dayOfMonth in 8..14){
                            semana = 2
                        } else if (current.dayOfMonth in 15..21){
                            semana = 3
                        } else{
                            semana = 4
                        }

                        db.collection("horizon_bar_chart").document(user.toString() + "@" + current.monthValue.toString() + "@" + semana.toString())
                            .get().addOnSuccessListener {
                                if ((it.get("horas").toString() == "null")){
                                    db.collection("horizon_bar_chart").document(user.toString() + "@" + current.monthValue.toString() + "@" + semana.toString())
                                        .set(mapOf(
                                            "horas" to horas_totales,
                                            "semana" to semana.toString(),
                                            "mes" to current.monthValue.toString(),
                                            "usuari" to user.toString()
                                    ))
                                } else{
                                    var horas_anteriores = it.get("horas").toString()
                                    var horas_nuevas = horas_totales + horas_anteriores.toFloat()
                                    db.collection("horizon_bar_chart").document(user.toString() + "@" + current.monthValue.toString() + "@" + semana.toString())
                                        .set(mapOf(
                                            "horas" to horas_nuevas,
                                            "semana" to semana.toString(),
                                            "mes" to current.monthValue.toString(),
                                            "usuari" to user.toString()
                                        ))
                                }
                            }

                        db.collection("bar_chart").document(user.toString() + "@" + data)
                            .set(mapOf(
                                "horas" to horas_totales,
                                "dia" to current.dayOfMonth.toString(),
                                "mes" to current.monthValue.toString(),
                                "año" to current.year.toString(),
                                "usuari" to user.toString()
                            ))


                        Toast.makeText(this, "Hours saved", Toast.LENGTH_LONG).show()
                        dialog.cancel()
                    }
                    dialog.setView(alertView)
                    dialog.show()
                }
            }


        val rate_last_sleep_button = findViewById<Button>(R.id.rate_last_sleep)
        rate_last_sleep_button.setOnClickListener {
            val intent = Intent(this@Home, Valuations::class.java)
            startActivity(intent)
        }

        val see_your_ratings_button = findViewById<Button>(R.id.see_your_ratings)
        see_your_ratings_button.setOnClickListener {
            val intent = Intent(this@Home, Buttons_ListCalendar::class.java)
            startActivity(intent)
        }

        val toggle_Wake_Sleep = findViewById<Switch>(R.id.toggle_Wake_Sleep)
        val timePicker_sleep_act = findViewById<TimePicker>(R.id.timePicker_sleep)
        val timePicker_wake_act = findViewById<TimePicker>(R.id.timePicker_wake)
        toggle_Wake_Sleep.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                toggle_Wake_Sleep.setText("WAKE")
                timePicker_wake_act.visibility = View.VISIBLE
                timePicker_sleep_act.visibility = View.GONE
            } else {
                toggle_Wake_Sleep.setText("SLEEP")
                timePicker_sleep_act.visibility = View.VISIBLE
                timePicker_wake_act.visibility = View.GONE
            }
        }
        hideKeyboardInputInTimePicker(this.resources.configuration.orientation, timePicker_sleep_act)
        hideKeyboardInputInTimePicker(this.resources.configuration.orientation, timePicker_wake_act)

        val button_set_alarm = findViewById<Button>(R.id.button_setAlarm)
        var alarmTitle: String
        var alarmHour: Int
        var alarmMinute : Int
        button_set_alarm.setOnClickListener {
                if (toggle_Wake_Sleep.isChecked) {
                    alarmTitle = "WAKE UP"
                    alarmHour = timePicker_wake_act.hour
                    alarmMinute = timePicker_wake_act.minute
                } else {
                    alarmTitle = "GO TO SLEEP"
                    alarmHour = timePicker_sleep_act.hour
                    alarmMinute = timePicker_sleep_act.minute
                }

                var minute : String
                val controller = Controller()
                if (alarmMinute<10){
                    minute = "0" + alarmMinute.toString()
                }
                else{
                    minute = alarmMinute.toString()
                }
                val hour: String = alarmHour.toString() + ":" + minute
                val weekDays: MutableMap<String, Boolean> =mutableMapOf(
                    "Monday" to true,
                    "Tuesday" to true,
                    "Wednesday" to true,
                    "Thursday" to true,
                    "Friday" to true,
                    "Saturday" to true,
                    "Sunday" to true
                )
                /*val userPreferences = getSharedPreferences("users", Context.MODE_PRIVATE)
                val user = userPreferences.getString("email", "")*/
                val alarm = controller.addAlarm(alarmTitle, hour, weekDays,user!!)

                calendar.set(Calendar.HOUR_OF_DAY,alarmHour)
                calendar.set(Calendar.MINUTE, alarmMinute)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                intent_reciver.putExtra("on/off","on")
                intent_reciver.putExtra("alarmTitle",alarmTitle)
                pi = PendingIntent.getBroadcast(this@Home,0,intent_reciver,
                    PendingIntent.FLAG_UPDATE_CURRENT)
                am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pi)
                Toast.makeText(this, "New alarm added", Toast.LENGTH_LONG).show()
        }

    }

    fun hideKeyboardInputInTimePicker(orientation: Int, timePicker: TimePicker)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            try
            {
                if (orientation == Configuration.ORIENTATION_PORTRAIT)
                {
                    ((timePicker.getChildAt(0) as LinearLayout).getChildAt(4) as LinearLayout).getChildAt(0).visibility = View.GONE
                }
                else
                {
                    (((timePicker.getChildAt(0) as LinearLayout).getChildAt(2) as LinearLayout).getChildAt(2) as LinearLayout).getChildAt(0).visibility = View.GONE
                }
            }
            catch (ex: Exception)
            {
            }

        }
    }
}
