package com.example.finalnagdni.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalnagdni.databinding.ItemPetBinding
import com.example.finalnagdni.model.Pet

class PetAdapter(private var pets: List<Pet>) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val binding = ItemPetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(pets[position])
    }

    override fun getItemCount(): Int = pets.size

    fun updatePets(newPets: List<Pet>) {
        pets = newPets
        notifyDataSetChanged()
    }

    inner class PetViewHolder(private val binding: ItemPetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pet: Pet) {
            binding.textViewPetName.text = pet.name
            binding.textViewPetBreed.text = pet.breed
            binding.textViewPetAge.text = pet.age.toString()
        }
    }
}
