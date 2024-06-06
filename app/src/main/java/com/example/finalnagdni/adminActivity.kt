package com.example.finalnagdni
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalnagdni.R

class AdminActivity : AppCompatActivity() {

    private lateinit var btnSave: Button
    private lateinit var edtDate: EditText
    private lateinit var edtTimeSlot: EditText
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        btnSave = findViewById(R.id.btnSave)
        edtDate = findViewById(R.id.edtDate)
        edtTimeSlot = findViewById(R.id.edtTimeSlot)

        dbHelper = DBHelper(this)

        btnSave.setOnClickListener {
            val date = edtDate.text.toString()
            val timeSlot = edtTimeSlot.text.toString()

            val result = dbHelper.insertAppointment(date, timeSlot, 0)
            if (result != -1L) {
                Toast.makeText(this, "Appointment saved successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to save appointment", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
