package com.example.finalelbueno.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.finalelbueno.R
import com.example.finalelbueno.databinding.FragmentHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject

private lateinit var database: DatabaseReference
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        val myDB = FirebaseDatabase.getInstance()
        database = myDB.reference

        database.child("users").child("002").get().addOnSuccessListener { record ->
            val json = JSONObject(record.value.toString())
            Log.d("ValoresFirebase", "got value ${record.value}")
            binding.tvName.setText(json.getString("nombre").toString())
            binding.tvLastname.setText(json.getString("apellido").toString())
            binding.tvnickName.setText(json.getString("nickname").toString())
            binding.tvNivel.setText(json.getString("level"))
            binding.tvNumPkmn.setText(json.getString("pokemonCapt"))
        }
        return binding.root
    }

}