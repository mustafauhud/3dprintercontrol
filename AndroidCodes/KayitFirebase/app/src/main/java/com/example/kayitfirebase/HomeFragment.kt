package com.example.kayitfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kayitfirebase.databinding.FragmentFavoriBinding
import com.example.kayitfirebase.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        val binding = FragmentHomeBinding.inflate(layoutInflater)
        var auth = FirebaseAuth.getInstance()
        var database = Firebase.database.reference
        val currentuser = FirebaseAuth.getInstance().uid

        var getdata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var nozzle_temperature = snapshot.child("users").child(currentuser!!).child("nozzle_temperature").getValue()
                var table_temperature = snapshot.child("users").child(currentuser!!).child("table_temperature").getValue()


                binding.nozzleTemperature.text = nozzle_temperature.toString()
                binding.tableTemperature.text = table_temperature.toString()


                var kullaniciadi = snapshot.child("users").child(currentuser!!).child("kullaniciadi").getValue()
                var yaziciadi = snapshot.child("users").child(currentuser!!).child("yaziciadi").getValue()

                binding.kullaniciadi.text = kullaniciadi.toString()
                binding.yaziciadi.text = yaziciadi.toString()


            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)












        return binding.root
    }
}