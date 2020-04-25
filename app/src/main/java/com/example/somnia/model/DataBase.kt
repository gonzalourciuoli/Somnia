package com.example.somnia.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class DataBase {
    private var db: FirebaseFirestore
    private var auth: FirebaseAuth


    constructor(){
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    fun addAlarm(alarm: Alarm){
        val user = auth.currentUser.toString()
        val alarm_map = alarm.toMap()
    }

    fun addValuation(valuation: Valuation){
        val valuation_map = valuation.toMap()
        db.collection("valuations")
            .add(valuation_map)
    }

    fun getValuations(): MutableList<Valuation>{
        var valu: Valuation?
        var valuationsList = mutableListOf<Valuation>()
        db.collection("valuations")
            .get().addOnSuccessListener {result ->
                for (valuation in result){
                    db.collection("valuations").document(valuation.id)
                        .get().addOnSuccessListener {
                            val user = it.get("user").toString()
                            val date = it.get("date").toString()
                            val numStars = it.get("numStars").toString()
                            val sport_box = it.get("sport_box").toString()
                            val coffee_box = it.get("coffee_box").toString()
                            val alcohol_box = it.get("alcohol_box").toString()
                            val valuation_comment = it.get("valuation_comment").toString()

                            valu = Valuation(user, date, numStars.toFloat(), sport_box.toBoolean(),
                                coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment)
                            valuationsList.add(valu!!)
                        }
                }
            }
        return valuationsList
    }

    fun getValuation(user: String, id: String): Valuation{
        var valu : Valuation? = null
        db.collection("valuations")
            .get().addOnSuccessListener {result ->
                for (valuation in result){
                    db.collection("valuation").document(valuation.id)
                        .get().addOnSuccessListener {
                            val user1 = it.get("user").toString()
                            val date = it.get("date").toString()
                            if(user1 == user && date == id){
                                val numStars = it.get("numStars").toString()
                                val sport_box = it.get("sport_box").toString()
                                val coffee_box = it.get("coffee_box").toString()
                                val alcohol_box = it.get("alcohol_box").toString()
                                val valuation_comment = it.get("valuation_comment").toString()

                                valu = Valuation(user, date, numStars.toFloat(), sport_box.toBoolean(),
                                    coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment)
                            }
                        }
                }
            }
        return valu!!
    }

    fun getValuationFromUser(user: String): MutableList<String>{
        var valu = ""
        var list = mutableListOf<String>()

        db.collection("valuations")
            .get().addOnSuccessListener {result ->
                for (valuation in result){
                    db.collection("valuation").document(valuation.id)
                        .get().addOnSuccessListener {
                            val user1 = it.get("user").toString()
                            if(user1 == user){
                                val date = it.get("date").toString()
                                val numStars = it.get("numStars").toString()
                                val sport_box = it.get("sport_box").toString()
                                val coffee_box = it.get("coffee_box").toString()
                                val alcohol_box = it.get("alcohol_box").toString()
                                val valuation_comment = it.get("valuation_comment").toString()

                                valu = Valuation(user, date, numStars.toFloat(), sport_box.toBoolean(),
                                    coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment).toString()
                            }
                        }
                }
            }
        return list
    }

    fun getValuationString(user: String, id: String): String {
        var valu = this.getValuation(user, id).toString()
        return valu
    }

    fun getListValuations(user: String): MutableList<String>{
        var list = mutableListOf<String>()
        list.add(this.getValuations().toString())
        return list
    }

    fun getAlarmsList(): MutableList<Alarm>{
        val user = auth.currentUser.toString()
        var alarmsList = mutableListOf<Alarm>()
        db.collection("Alarms").document("alarm"+user).collection("Alarms").get().addOnSuccessListener {list ->
                    for (alarm in list){
                        db.collection("Alarms").document("alarm"+user).collection("Alarms").document(alarm.id)
                            .get().addOnSuccessListener {
                                val title = it.get("Title").toString()
                                val hour = it.get("Hour").toString()
                                val status = it.get("Alarm on").toString()
                                val monday = it.get("Monday").toString()
                                val tuesday = it.get("Tuesday").toString()
                                val wednesday = it.get("Wednesday").toString()
                                val thursday = it.get("Thursday").toString()
                                val friday = it.get("Friday").toString()
                                val saturday = it.get("Saturday").toString()
                                val sunday = it.get("Sunday").toString()
                                val weekDays: MutableMap<String, Boolean> = mutableMapOf(
                                    "Monday" to monday.toBoolean(),
                                    "Tuesday" to tuesday.toBoolean(),
                                    "Wednesday" to wednesday.toBoolean(),
                                    "Thursday" to thursday.toBoolean(),
                                    "Friday" to friday.toBoolean(),
                                    "Saturday" to saturday.toBoolean(),
                                    "Sunday" to sunday.toBoolean())
                                val id = getAlarmId(it.reference)
                                val alarm = Alarm(title,hour,weekDays)
                                alarm.setId(id)
                                alarm.setStatus(status.toBoolean())
                                alarmsList.add(alarm)

                            }
                        }
                    }
        return alarmsList
    }

    fun getAlarmId(alarm: DocumentReference): String{
        return alarm.id
    }

    /*fun changeStatus(alarm: Alarm){
        val user = auth.currentUser.toString()
        db.collection("Alarms").document("alarm"+user).collection("Alarms").document(alarm.getId())
        alarm.changeStatus()
    }*/

    fun changePassword(password: String){
        auth.currentUser?.updatePassword(password)
        db.collection("users").document(password).update("password",password)
    }

}




