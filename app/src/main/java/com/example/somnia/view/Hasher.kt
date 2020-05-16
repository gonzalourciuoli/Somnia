package com.example.somnia.view

import java.security.MessageDigest

class Hasher {

    //Funcion SHA-256 para encriptar contraseñas
    fun hash(base:String):String{
        val bytes = base.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("",{str,it -> str+"%02x".format(it)})
        //return digest.fold(" ", {str,it -> str+it})
    }
}