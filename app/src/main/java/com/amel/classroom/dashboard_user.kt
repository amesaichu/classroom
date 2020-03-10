package com.amel.classroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class dashboard_user : AppCompatActivity() {

    private lateinit var b_profile: Button
    private lateinit var b_form: Button
    private lateinit var b_jadwal: Button
    private lateinit var b_histori: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_user)

        b_profile = findViewById(R.id.b_profile)
        b_profile.setOnClickListener{
            startActivity(Intent(this, profile_user::class.java))
        }


        b_form = findViewById(R.id.b_form)
        b_form.setOnClickListener{
            startActivity(Intent(this, form_user::class.java))
        }


        b_jadwal = findViewById(R.id.b_jadwalruang)
        b_jadwal.setOnClickListener{
            startActivity(Intent(this, jadwal_ruang::class.java))
        }


        b_histori = findViewById(R.id.b_history)
        b_histori.setOnClickListener{
            startActivity(Intent(this, history::class.java))
        }


    }
}
