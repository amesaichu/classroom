package com.amel.classroom

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_user_mhs.*

class add_user_mhs : AppCompatActivity() {
    private var etNim: EditText? = null
    private var etNama: EditText? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var etTelepon: EditText? = null
    private var btnCreateAccount: Button? = null
    private var mProgressBar: ProgressDialog? = null

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "CreateAccount"

    private var nim: String? = null
    private var nama: String? = null
    private var email: String? = null
    private var password: String? = null
    private var telepon: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user_mhs)

        val arrayuser = resources.getStringArray(R.array.auser)
        val spinnerUser = findViewById<Spinner>(R.id.spinner_status_add)
        if (spinnerUser != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayuser
            )
            spinnerUser.adapter = adapter
            spinnerUser.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@add_user_mhs,
                        getString(R.string.auser_item) + " " +
                                "" + arrayuser[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        initialise()
    }

    private fun initialise() {
        etNim = findViewById<View>(R.id.Input_nim_add) as EditText
        etNama = findViewById<View>(R.id.Input_nama_add) as EditText
        etEmail = findViewById<View>(R.id.Input_email_add) as EditText
        etPassword = findViewById<View>(R.id.Input_pass_add) as EditText
        etTelepon = findViewById<View>(R.id.Input_telp_add) as EditText
        btnCreateAccount = findViewById<View>(R.id.bt_tambah_user) as Button
        mProgressBar = ProgressDialog(this)
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        btnCreateAccount!!.setOnClickListener { CreateAccount() }
    }

    private fun CreateAccount() {
        nim = etNim?.text.toString()
        nama = etNama?.text.toString()
        email = etEmail?.text.toString()
        telepon = etTelepon?.text.toString()
        password = etPassword?.text.toString()
        val spinnerUser = spinner_status_add?.getSelectedItem().toString()
        val password1 = etPassword!!.text.toString().trim()
        if (password1.length < 6){
            Toast.makeText(applicationContext,"Password too short, enter mimimum 6 charcters" , Toast.LENGTH_LONG).show()
        }else{
            if (!TextUtils.isEmpty(nim) && !TextUtils.isEmpty(nama) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(telepon)) {
                mProgressBar!!.setMessage("Registering User...")
                mProgressBar!!.show()

                mAuth!!
                    .createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this) { task ->
                        mProgressBar!!.hide()
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val userId = mAuth!!.currentUser!!.uid
                            //Verify Email
                            verifyEmail();
                            //update user profile information
                            val currentUserDb = mDatabaseReference!!.child(userId)
                            currentUserDb.child("telepon").setValue(telepon)
                            currentUserDb.child("nim").setValue(nim)
                            currentUserDb.child("nama").setValue(nama)
                            currentUserDb.child("hakAkses").setValue(spinnerUser)
                            updateUserInfoAndUI()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUserInfoAndUI() {
        //start next activity
        val intent = Intent(this, dashboard_admin::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "Verification email sent to " + mUser.getEmail(),
                        Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(this,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
