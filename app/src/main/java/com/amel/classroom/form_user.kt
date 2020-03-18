package com.amel.classroom

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_form_user.*
import java.util.*

class form_user : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var datePicker: DatePickerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_user)
        datePicker = DatePickerHelper(this)
        ETdate.setOnClickListener {
            showDatePickerDialog()
        }

        val arrayruang = resources.getStringArray(R.array.aruang)
        val spinnerruang = findViewById<Spinner>(R.id.sp_ruang)
        if (spinnerruang != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayruang
            )
            spinnerruang.adapter = adapter
            spinnerruang.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@form_user,
                        getString(R.string.aruang_item) + " " +
                                "" + arrayruang[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        val arrayjam = resources.getStringArray(R.array.ajam)
        val spinnerjam = findViewById<Spinner>(R.id.sp_jam)
        if (spinnerjam != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayjam
            )
            spinnerjam.adapter = adapter
            spinnerjam.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@form_user,
                        getString(R.string.ajam_item) + " " +
                                "" + arrayjam[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val arrayjam2 = resources.getStringArray(R.array.ajam2)
        val spinnerjam2 = findViewById<Spinner>(R.id.sp_jam2)
        if (spinnerjam2 != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayjam2
            )
            spinnerjam2.adapter = adapter
            spinnerjam2.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@form_user,
                        getString(R.string.ajam2_item) + " " +
                                "" + arrayjam2[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val arraymatkul = resources.getStringArray(R.array.amatkul)
        val spinnermatkul = findViewById<Spinner>(R.id.sp_matkul)
        if (spinnermatkul != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arraymatkul
            )
            spinnermatkul.adapter = adapter
            spinnermatkul.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@form_user,
                        getString(R.string.amatkul_item) + " " +
                                "" + arraymatkul[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        ref = FirebaseDatabase.getInstance().getReference("Pinjam")
        btnSubmit.setOnClickListener {
            savedata()
            val intent = Intent (this, dashboard_user::class.java)
            startActivity(intent)
        }
    }
    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        val minDate = Calendar.getInstance()
        minDate.set(Calendar.HOUR_OF_DAY, 0)
        minDate.set(Calendar.MINUTE, 0)
        minDate.set(Calendar.SECOND, 0)
        datePicker.setMinDate(minDate.timeInMillis)
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 7)
        datePicker.setMaxDate(maxDate.timeInMillis)
        datePicker.showDialog(d, m, y, object : DatePickerHelper.Callback {
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                ETdate.text = "${dayStr}-${monthStr}-${year}"
            }
        })
    }

    private fun savedata() {

        val nomor = ETid.text.toString()
        val nama = ETnama.text.toString()
        val kelas = ETkelas.text.toString()
        val tanggal = ETdate.text.toString()
        val ruang = sp_ruang.getSelectedItem().toString()
        val wp = sp_jam.getSelectedItem().toString()
        val ws = sp_jam2.getSelectedItem().toString()
        val matkul = sp_matkul.getSelectedItem().toString()
        val alas = alasan.text.toString()

        val Peminjaman = peminjaman(nomor,nama,kelas,tanggal,ruang,wp,ws,matkul,alas)

        ref.child(nomor).setValue(Peminjaman).addOnCompleteListener {
            Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show()
            ETid.setText("")
            ETnama.setText("")
            ETkelas.setText("")
            ETdate.setText("")
            alasan.setText("")

        }
    }
}