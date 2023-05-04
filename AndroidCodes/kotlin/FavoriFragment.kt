package com.example.kayitfirebase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kayitfirebase.R
import com.example.kayitfirebase.databinding.ActivityAkisBinding
import com.example.kayitfirebase.databinding.FragmentFavoriBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FavoriFragment : Fragment() {
    private lateinit var binding: FragmentFavoriBinding
    lateinit var database : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriBinding.inflate(inflater,container,false)

        val binding = FragmentFavoriBinding.inflate(layoutInflater)
        var auth = FirebaseAuth.getInstance()
        var database = Firebase.database.reference
        val currentuser = FirebaseAuth.getInstance().uid

        var getdata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var akismail = snapshot.child("users").child(currentuser!!).child("email").getValue()
                var akissifre = snapshot.child("users").child(currentuser!!).child("sifre").getValue()
                var yaziciyazdir = snapshot.child("users").child(currentuser!!).child("yaziciDurum").getValue()

                binding.mail.text = akismail.toString()
                binding.sifre.text = akissifre.toString()
                binding.yazicidurum.text = yaziciyazdir.toString()
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