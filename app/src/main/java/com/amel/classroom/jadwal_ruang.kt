package com.amel.classroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class jadwal_ruang : AppCompatActivity() {

    private lateinit var button_sen: Button
    private lateinit var button_sel: Button
    private lateinit var button_rabu: Button
    private lateinit var button_kam: Button
    private lateinit var button_jum: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_ruang)

        setTitle("Jadwal Harian Ruang")


        button_sen = findViewById(R.id.b_senin)
        button_sen.setOnClickListener{
            startActivity(Intent(this, t_senin::class.java))
        }


        button_sel = findViewById(R.id.b_selasa)
        button_sel.setOnClickListener{
            startActivity(Intent(this, t_selasa::class.java))
        }


        button_rabu = findViewById(R.id.b_rabu)
        button_rabu.setOnClickListener{
            startActivity(Intent(this, t_rabu::class.java))
        }


        button_kam = findViewById(R.id.b_kamis)
        button_kam.setOnClickListener{
            startActivity(Intent(this, t_kamis::class.java))
        }


        button_jum = findViewById(R.id.b_jumat)
        button_jum.setOnClickListener{
            startActivity(Intent(this, t_jumat::class.java))
        }


    }
}
