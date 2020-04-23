package com.example.somnia.model

import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.properties.Delegates
import android.content.Context

class Valuation {

    private var user : String
    private var date : String
    private var numStars : Float
    private var sport_box : Boolean
    private var coffee_box : Boolean
    private var alcohol_box : Boolean
    private var valuation_comment : String
    private var db : FirebaseFirestore

    constructor(user: String, date : String, numStars : Float, sport_box : Boolean, coffee_box : Boolean,
                alcohol_box : Boolean, valuation_comment : String){
        this.user = user
        this.date = date
        this.numStars = numStars
        this.sport_box = sport_box
        this.coffee_box = coffee_box
        this.alcohol_box = alcohol_box
        this.valuation_comment = valuation_comment

        db = FirebaseFirestore.getInstance()
    }

    fun addValuation(){
        /*db.collection("valuations").document(user).set(mapOf(
                "date" to date,
                "numStars" to numStars,
                "sport_box" to sport_box,
                "coffee_box" to coffee_box,
                "alcohol_box" to alcohol_box,
                "valuation_comment" to valuation_comment
            ))*/
        db.collection("valuations")
            .add(mapOf(
                "user" to user,
                "date" to date,
                "numStars" to numStars,
                "sport_box" to sport_box,
                "coffee_box" to coffee_box,
                "alcohol_box" to alcohol_box,
                "valuation_comment" to valuation_comment
            ))
    }

    fun getUserValuation(): String{
        return this.user
    }

    fun getDateValuation(): String{
        return this.date
    }

    fun getNumStarsVaulation(): Float{
        return this.numStars
    }

    fun getSportBoxValuation(): Boolean{
        return this.sport_box
    }

    fun getCoffeeBoxValuation(): Boolean{
        return this.coffee_box
    }

    fun getAlcoholBoxValuation(): Boolean{
        return this.alcohol_box
    }

    fun getValuationComment(): String{
        return this.valuation_comment
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
                    valu = this.toString(date, numStars.toFloat(), sport_box.toBoolean(),
                        coffee_box.toBoolean(), alcohol_box.toBoolean(), valuation_comment)
                }
            }

        return valu
    }

    override fun toString(): String{
        var informacio = ""
        if (date == null || date == "null"){
            informacio = "No valuation on this day"
        }else{
            informacio += ("Rating: " + numStars.toString() + " / 5 \n")
            if (sport_box == true){
                informacio += ("Sport: Yes \n")
            }else{
                informacio += ("Sport: No \n")
            }
            if (coffee_box == true){
                informacio += ("Coffee: Yes \n")
            }else{
                informacio += ("Coffee: No \n")
            }
            if (alcohol_box == true){
                informacio += ("Alcohol: Yes \n")
            }else{
                informacio += ("Alcohol: No \n")
            }
            if (valuation_comment != ""){
                informacio += (valuation_comment)
            }else{
                informacio += ("No comments")
            }
        }

        return informacio
    }


    fun toString(date: String, numStars: Float, sport_box: Boolean, coffee_box: Boolean,
                 alcohol_box: Boolean, valuation_comment: String): String{
        var informacio = ""
        if (date == null || date == "null"){
            informacio = "No valuation on this day"
        }else{
            informacio += ("Rating: " + numStars.toString() + " / 5 \n")
            if (sport_box == true){
                informacio += ("Sport: Yes \n")
            }else{
                informacio += ("Sport: No \n")
            }
            if (coffee_box == true){
                informacio += ("Coffee: Yes \n")
            }else{
                informacio += ("Coffee: No \n")
            }
            if (alcohol_box == true){
                informacio += ("Alcohol: Yes \n")
            }else{
                informacio += ("Alcohol: No \n")
            }
            if (valuation_comment != ""){
                informacio += (valuation_comment)
            }else{
                informacio += ("No comments")
            }
        }

        return informacio
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
}