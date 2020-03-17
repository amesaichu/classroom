package com.amel.classroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class dashboard_admin : AppCompatActivity() {

    private lateinit var buser: Button
    private lateinit var bmatkul:Button
    private lateinit var bpinjaman: Button
    private lateinit var bprofile: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_admin)

        setTitle("Admin Dashboard")

        bprofile=findViewById(R.id.b_profile)
        bprofile.setOnClickListener{
            startActivity(Intent(this, profil::class.java))
        }


        bmatkul=findViewById(R.id.b_matkul)
        bmatkul.setOnClickListener{
            startActivity(Intent(this,matkul_list::class.java))
        }

        bpinjaman=findViewById(R.id.b_pinjam)
//        bpinjaman.setOnClickListener{
//            startActivity(Intent(this, list::class.java))
//        }


        buser=findViewById(R.id.b_user)
        buser.setOnClickListener{
            startActivity(Intent(this, add_user_mhs::class.java))
        }
    }
}
