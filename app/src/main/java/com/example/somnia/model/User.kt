package com.example.somnia.model

import com.example.somnia.view.ChangePassword

class User {
    private var username: String
    private var email: String
    private var password: String

    constructor(username: String, email: String, password: String){
        this.username = username
        this.email = email
        this.password = password
    }

    fun toMap(): Map<String,Any>{
        return mutableMapOf(
            "username" to username,
            "email" to email,
            "password" to password
        )
    }

    fun getUsername(): String{
        return username
    }

    fun getEmail(): String{
        return email
    }

    fun getPassword(): String{
        return password
    }


}