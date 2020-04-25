package com.example.somnia.model

class Calculator {
    private lateinit var timeWakeUp : String
    private lateinit var hoursToSleep : String
    private lateinit var  timeBed: String

    constructor(timeWakeUp : String, hoursToSleep : String, timeBed: String ){
        this.timeWakeUp = timeWakeUp
        this.hoursToSleep = hoursToSleep
        this.timeBed = timeBed
    }
    fun calculateTimeToGoBed(): String{
        var hoursWakeUp = (this.timeWakeUp.substringBefore(":")).toInt()
        var minutsWakeUp = (this.timeWakeUp.substringAfter(":")).toInt()
        var hours = (this.hoursToSleep.substringBefore(":")).toInt()
        var minuts = (this.hoursToSleep.substringAfter(":")).toInt()

        var resultHours = (hoursWakeUp - hours)
        var resultMinuts = (minutsWakeUp - minuts)
        if (resultHours < 0){
            var i: Int
            i = resultHours + 24
            resultHours = i
        }
        if (resultMinuts < 0){
            var i : Int
            i = resultMinuts + 60
            resultMinuts = i
            resultHours -= 1
        }
        val result = resultHours.toString() + ":" + resultMinuts.toString()
        return result
    }


    fun calculateTimeToWakeUp(): String{
        var hoursBed = (this.timeBed.substringBefore(":")).toInt()
        var minutsBed = (this.timeBed.substringAfter(":")).toInt()
        var hours = (this.hoursToSleep.substringBefore(":")).toInt()
        var minuts = (this.hoursToSleep.substringAfter(":")).toInt()

        var resultHours = (hoursBed + hours)
        var resultMinuts = (minutsBed + minuts)
        if (resultHours >= 24){
            var i: Int
            i = resultHours - 24
            resultHours = i
        }
        if (resultMinuts >= 60){
            var i : Int
            i = resultMinuts - 60
            resultMinuts = i
            resultHours += 1
        }
        val result = resultHours.toString() + ":" + resultMinuts.toString()
        return result
    }
}