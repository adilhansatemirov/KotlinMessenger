package com.example.kotlinmessenger

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener{
            performLogin()
        }

        back_to_register_textview_login.setOnClickListener {
            finish()
        }
    }

    private fun performLogin(){
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this,"Please, enter email/password", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("Login", "Email: $email")
        Log.d("Login", "Password: $password")

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful) {
                    return@addOnCompleteListener
                }
                Log.d("Login", "Logged in: ${it.result?.user?.uid}")
            }
            .addOnFailureListener{
                Log.d("Login","Failed: ${it.message}")
            }
    }
}