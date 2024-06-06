package com.example.finalnagdni

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalnagdni.databinding.ActivityScheduleBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.material.button.MaterialButton
import java.util.*

class ScheduleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleBinding
    private val db = FirebaseFirestore.getInstance()
    private var selectedDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val monthString = (month + 1).toString().padStart(2, '0')
            val dayString = dayOfMonth.toString().padStart(2, '0')
            selectedDate = "$year-$monthString-$dayString"
            loadTimeSlots(selectedDate!!)
        }

        binding.buttonBack.setOnClickListener {
            // Implement back button functionality here
        }

        binding.SaveScheduleButton.setOnClickListener {
            // Implement save functionality here
        }
    }

    private fun loadTimeSlots(date: String) {
        binding.timeSlotContainer.removeAllViews()
        db.collection("appointments").whereEqualTo("date", date).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val appointment = document.toObject(Appointment::class.java)
                    addTimeSlotButton(appointment.time)
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error loading time slots: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addTimeSlotButton(time: String) {
        val button = MaterialButton(this)
        button.text = time
        button.setOnClickListener {
            bookAppointment(time)
        }
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 8, 8, 8)
        binding.timeSlotContainer.addView(button, layoutParams)
    }

    private fun bookAppointment(time: String) {
        val user = hashMapOf(
            "userId" to "exampleUserId", // Replace with actual user ID
            "name" to "Example User", // Replace with actual user name
            "appointmentTime" to time,
            "date" to selectedDate
        )
        db.collection("bookings").add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Appointment booked successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error booking appointment: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}

data class Appointment(
    val date: String = "",
    val time: String = ""
)
