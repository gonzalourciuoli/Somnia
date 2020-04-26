package com.example.somnia.model

class Valuation {

    private var user : String
    private var date : String
    private var numStars : Float
    private var sport_box : Boolean
    private var coffee_box : Boolean
    private var alcohol_box : Boolean
    private var valuation_comment : String

    constructor(user: String, date : String, numStars : Float, sport_box : Boolean, coffee_box : Boolean,
                alcohol_box : Boolean, valuation_comment : String){
        this.user = user
        this.date = date
        this.numStars = numStars
        this.sport_box = sport_box
        this.coffee_box = coffee_box
        this.alcohol_box = alcohol_box
        this.valuation_comment = valuation_comment
    }

    fun toMap(): Map<String, Any?>{
        return mapOf(
            "user" to user,
            "date" to date,
            "numStars" to numStars,
            "sport_box" to sport_box,
            "coffee_box" to coffee_box,
            "alcohol_box" to alcohol_box,
            "valuation_comment" to valuation_comment
        )
    }

    fun getUserValuation(): String{
        return this.user
    }

    fun getDateValuation(): String{
        return this.date
    }

    fun getNumStarsValuation(): String{
        return this.numStars.toString()
    }

    fun getSportBoxValuation(): String{
        return this.sport_box.toString()
    }

    fun getCoffeeBoxValuation(): String{
        return this.coffee_box.toString()
    }

    fun getAlcoholBoxValuation(): String{
        return this.alcohol_box.toString()
    }

    fun getValuationComment(): String{
        return this.valuation_comment
    }

    override fun toString(): String{
        var informacio = ""
        if (date == null || date == "null" || date == ""){
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

    fun toStringWithDate(): String{
        var informacio = ""
        informacio += (date + "\n")
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

        return informacio

    }
}