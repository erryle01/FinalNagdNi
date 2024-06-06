package com.example.finalnagdni

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalnagdni.databinding.ItemAppointmentBinding

class AppointmentAdapter(
    private val appointments: List<Appointment>,
    private val onClick: (Appointment) -> Unit
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = ItemAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppointmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(appointments[position], onClick)
    }

    override fun getItemCount() = appointments.size

    class AppointmentViewHolder(private val binding: ItemAppointmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appointment: Appointment, onClick: (Appointment) -> Unit) {
            binding.appointmentTime.text = "${appointment.date} ${appointment.time}"
            binding.bookButton.setOnClickListener { onClick(appointment) }
        }
    }
}
