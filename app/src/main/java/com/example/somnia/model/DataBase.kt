package com.example.somnia.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

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
        db.collection("Alarms").document(user).set(alarm_map)
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
        db.collection("valuations").document(user).collection(id).document("data")
            .get().addOnSuccessListener {
                val date = it.get("date").toString()
                val numStars = it.get("numStars").toString()
                val sport_box = it.get("sport_box").toString()
                val coffee_box = it.get("coffee_box").toString()
                val alcohol_box = it.get("alcohol_box").toString()
                val valuation_comment = it.get("valuation_comment").toString()

                if (date != null || date != "null"){
                    valu = Valuation(user, date, numStars.toFloat(), sport_box.toBoolean(),
                        coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment)
                }
            }
        return valu!!
    }

    fun getValuationString(user: String, id: String): String {
        var valu = ""
        db.collection("valuations").document(user).collection(id).document("data")
            .get().addOnSuccessListener {
                val date = it.get("date").toString()
                val numStars = it.get("numStars").toString()
                val sport_box = it.get("sport_box").toString()
                val coffee_box = it.get("coffee_box").toString()
                val alcohol_box = it.get("alcohol_box").toString()
                val valuation_comment = it.get("valuation_comment").toString()

                if (date != null || date != "null"){
                    val valuation = Valuation(user, date, numStars.toFloat(), sport_box.toBoolean(),
                        coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment)
                    valu = valuation.toString(date, numStars.toFloat(), sport_box.toBoolean(),
                        coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment)
                }
            }

        return valu
    }

    /*fun getAlarmsList(): MutableList<Alarm>{
        return
    }*/


}
