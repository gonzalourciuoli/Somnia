package com.example.somnia.model

class Calculator {
    private var timeWakeUp : String
    private var hoursToSleep : String
    private var  timeBed: String

    constructor(timeWakeUp : String, hoursToSleep : String, timeBed: String ){
        this.timeWakeUp = timeWakeUp
        this.hoursToSleep = hoursToSleep
        this.timeBed = timeBed
    }
    fun calculateTimeToGoBed(): String{
        val hoursWakeUp = (this.timeWakeUp.substringBefore(":")).toInt()
        val minutsWakeUp = (this.timeWakeUp.substringAfter(":")).toInt()
        val hours = (this.hoursToSleep.substringBefore(":")).toInt()
        val minuts = (this.hoursToSleep.substringAfter(":")).toInt()



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
        return (resultHours.toString() + ":" + resultMinuts.toString())
    }


    fun calculateTimeToWakeUp(): String{
        val hoursBed = (this.timeBed.substringBefore(":")).toInt()
        val minutsBed = (this.timeBed.substringAfter(":")).toInt()
        val hours = (this.hoursToSleep.substringBefore(":")).toInt()
        val minuts =(this.hoursToSleep.substringAfter(":")).toInt()

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
        return (resultHours.toString() + ":" + resultMinuts.toString())
    }
}