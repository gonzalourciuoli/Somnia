package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_change_email.*

class ChangeEmail : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var userCollection : CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userCollection = db.collection("users")
    }

    fun save_changes(view: View) {
        changeEmail()
        startActivity(Intent(this, Settings::class.java))
    }

    fun ret(view: View) {
        startActivity(Intent(this, Settings::class.java))
    }

    private fun changeEmail() {
        val current_Email : String = current_Email.text.toString()
        val password : String = password.text.toString()
        val new_Email : String = new_Email.text.toString()

        if(!TextUtils.isEmpty(current_Email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(new_Email)) {
            auth.signInWithEmailAndPassword(current_Email, password).addOnCompleteListener {
                task ->

                if (task.isComplete) {
                    auth.currentUser?.updateEmail(new_Email)
                    userCollection.document(current_Email).get().addOnSuccessListener {
                        val base_username : String? = it.getString("username")
                        val base_password : String? = it.getString("password")
                        val user = hashMapOf(
                            "username" to base_username,
                            "email" to new_Email,
                            "password" to base_password
                        )
                        userCollection
                            .document(new_Email).set(user)
                        userCollection
                            .document(current_Email).delete()
                    }
                } else {
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "Looks like some of the fields are empty", Toast.LENGTH_LONG).show()
        }
    }

}
