package com.example.finalnagdni.view

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.finalnagdni.R

class Feedback : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val Buttonback: Button = findViewById(R.id.buttonBack)
        // Handle back button click event
        Buttonback.setOnClickListener {
            onBackPressed() // Go back to the previous activity
        }
    }

}