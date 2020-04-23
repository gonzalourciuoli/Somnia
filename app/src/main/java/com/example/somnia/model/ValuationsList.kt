package com.example.somnia.model

import com.google.firebase.firestore.FirebaseFirestore

class ValuationsList {
    private var valuationsList:MutableList<Valuation>
    private var db: FirebaseFirestore
    private var valuation: Valuation? = null

    constructor(){
        valuationsList = mutableListOf<Valuation>()
        db = FirebaseFirestore.getInstance()

        initList()
    }

    fun initList(){
        var valu: Valuation?
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
    }

    fun addValuation(newValuation: Valuation){
        newValuation.addValuation()
        valuationsList.add(newValuation)
    }

    fun getValuationsList(): MutableList<Valuation>{
        return this.valuationsList
    }

    fun getValuationString(user: String, id: String): String{
        var valu = ""
        for (valuation in valuationsList){
            if (valuation.getUserValuation() == user){
                if (valuation.getDateValuation() == id){
                    valu = valuation.toString()
                }
            }
        }
        return valu
    }

    fun getList(user: String): MutableList<String>{
        var list = mutableListOf<String>()
        var valu = ""
        for (valuation in valuationsList){
            if (valuation.getUserValuation() == user){
                valu = valuation.toString()
                list.add(valu)
            }
        }
        return list
    }

    fun getValuation(user: String, id: String): Valuation? {
        return valuation?.getValuation(user, id)
    }
}