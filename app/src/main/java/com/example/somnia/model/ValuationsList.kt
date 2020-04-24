package com.example.somnia.model


class ValuationsList {
    private var valuationsList:MutableList<Valuation>
    private var valuation: Valuation? = null
    private var data_base: DataBase

    constructor(){
        valuationsList = mutableListOf<Valuation>()
        data_base = DataBase()

        initList()
    }

    private fun initList(){
        valuationsList = data_base.getValuations()
    }

    fun addValuation(newValuation: Valuation){
        //newValuation.addValuation()
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

    fun getValuation(user: String, id: String): Valuation {
        return data_base.getValuation(user, id)
    }
}