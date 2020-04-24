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


}