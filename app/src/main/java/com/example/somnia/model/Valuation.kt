package com.example.somnia.model

import kotlin.properties.Delegates

class Valuation {

    var date : String
    var numStars : Int
    var sport_box : Boolean
    var coffee_box : Boolean
    var alcohol_box : Boolean
    var valuation_comment : String

    constructor(date : String, numStars : Int, sport_box : Boolean, coffee_box : Boolean,
                alcohol_box : Boolean, valuation_comment : String){
        this.date = date
        this.numStars = numStars
        this.sport_box = sport_box
        this.coffee_box = coffee_box
        this.alcohol_box = alcohol_box
        this.valuation_comment = valuation_comment

    }

}