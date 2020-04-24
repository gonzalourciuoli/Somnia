package com.example.somnia.model

class Valuation {
    private var date: String
    private var rating: Int
    private var sport_box: Boolean
    private var coffee_box: Boolean
    private var alcohol_box: Boolean
    private var valuation_comment: String

    constructor(date: String, rating: Int, sport_box: Boolean, coffee_box: Boolean, alcohol_box: Boolean, valuation_comment: String){
        this.date = date
        this.rating = rating
        this.sport_box = sport_box
        this.coffee_box = coffee_box
        this.alcohol_box = alcohol_box
        this.valuation_comment = valuation_comment
    }
}