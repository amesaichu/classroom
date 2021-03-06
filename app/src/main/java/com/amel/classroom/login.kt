package com.amel.classroom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*


class login : AppCompatActivity() {

    private lateinit var b_login: Button
    private lateinit var b_log_ad: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setTitle("Login MyClassroom")

//        login auth firebase

        b_login = findViewById(R.id.button_login)
        b_login.setOnClickListener{
            val email = input_id.text.toString()
            val password = input_password.text.toString()
            if (email.isEmpty()|| password.isEmpty()) {
                Toast.makeText(this, "Please Insert Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{

                    if (!it.isSuccessful){ return@addOnCompleteListener
                        val intent = Intent (this, login::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "Succesfully Login", Toast.LENGTH_SHORT).show()
                        val intent = Intent (this, dashboard_user::class.java)
                        startActivity(intent)
                    }
                }
                .addOnFailureListener{
                    Log.d("Main", "Failed Login: ${it.message}")
                    Toast.makeText(this, "Email/Password incorrect", Toast.LENGTH_SHORT).show()
                }
        }

        b_log_ad = findViewById(R.id.button_loginadmin)
        b_log_ad.setOnClickListener{
            val email = input_id.text.toString()
            val password = input_password.text.toString()
            if (email.isEmpty()|| password.isEmpty()) {
                Toast.makeText(this, "Please Insert Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{

                    if (!it.isSuccessful){ return@addOnCompleteListener
                        val intent = Intent (this, login::class.java)
                        startActivity(intent)
                    }
                    else
                        Toast.makeText(this, "Succesfully Login", Toast.LENGTH_SHORT).show()
                        val intent = Intent (this, dashboard_admin::class.java)
                        startActivity(intent)
                }
                .addOnFailureListener{
                    Log.d("Main", "Failed Login: ${it.message}")
                    Toast.makeText(this, "Email/Password incorrect", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
