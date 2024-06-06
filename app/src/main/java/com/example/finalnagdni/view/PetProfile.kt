package com.example.finalnagdni.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalnagdni.databinding.ActivityPetProfileBinding
import com.example.finalnagdni.databinding.DialogAddPetBinding
import com.example.finalnagdni.model.Pet
import com.example.finalnagdni.model.PetDatabaseHelper
import com.example.finalnagdni.view.adapter.PetAdapter

class PetProfile : AppCompatActivity() {

    private lateinit var binding: ActivityPetProfileBinding
    private lateinit var dbHelper: PetDatabaseHelper
    private lateinit var petAdapter: PetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPetProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = PetDatabaseHelper(this)
        petAdapter = PetAdapter(dbHelper.getAllPets())

        binding.recyclerViewPets.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPets.adapter = petAdapter

        binding.fabAddPet.setOnClickListener {
            showAddPetDialog()
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun showAddPetDialog() {
        val dialog = Dialog(this)
        val dialogBinding = DialogAddPetBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(dialogBinding.root)

        dialogBinding.buttonSavePet.setOnClickListener {
            val name = dialogBinding.editTextPetName.text.toString().trim()
            val breed = dialogBinding.editTextPetBreed.text.toString().trim()
            val ageText = dialogBinding.editTextPetAge.text.toString().trim()

            if (name.isNotEmpty() && breed.isNotEmpty() && ageText.isNotEmpty()) {
                try {
                    val age = ageText.toInt()
                    if (age > 0) {
                        val newPet = Pet(name = name, breed = breed, age = age)
                        dbHelper.addPet(newPet)
                        petAdapter.updatePets(dbHelper.getAllPets())
                        dialog.dismiss()
                    } else {
                        Toast.makeText(this, "Age must be greater than 0", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }
}
