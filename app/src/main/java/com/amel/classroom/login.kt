package com.amel.classroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {

    private lateinit var b_login: Button
    lateinit var b_log_ad: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setTitle("Login MyClassroom")

        b_login = findViewById(R.id.button_login)
        b_login.setOnClickListener{
            startActivity(Intent(this, dashboard_user::class.java))
        }

        b_log_ad = findViewById(R.id.button_loginadmin)
        b_log_ad.setOnClickListener{
            startActivity(Intent(this, dashboard_admin::class.java))
        }
    }
}
