package com.amel.classroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class profile_user : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)

       setTitle("Profil")

        auth = FirebaseAuth.getInstance()
        val users = auth.currentUser

        val et_id : EditText = findViewById(R.id.nim)
        val et_nama : EditText = findViewById(R.id.nama)
        val et_status : EditText = findViewById(R.id.status)

        var user: User? = null  // declare user object outside onCreate Method

        var ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        // Read from the database
        val menuListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue(User::class.java)
                et_status.setText(user?.hakAkses)
                et_nama.setText(user?.nama)
                et_id.setText(user?.nim)
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}
