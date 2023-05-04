package com.example.kayitfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kayitfirebase.databinding.ActivityAkisBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Akis : AppCompatActivity() {

    // BOŞLUK SADECE ÖRNEK AMAÇLI

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAkisBinding.inflate(layoutInflater)
        var auth = FirebaseAuth.getInstance()
        var database = Firebase.database.reference
        val currentuser = FirebaseAuth.getInstance().uid
        setContentView(binding.root)

        var getdata = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var akismail = snapshot.child("users").child(currentuser!!).child("email").getValue()
                var akissifre = snapshot.child("users").child(currentuser!!).child("sifre").getValue()
                var yaziciyazdir = snapshot.child("users").child(currentuser!!).child("yaziciDurum").getValue()

                binding.akisMail.text = akismail.toString()
                binding.akisSifre.text = akissifre.toString()
                binding.akisYaziciDurum.text = yaziciyazdir.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

        binding.btnYaziciDegis.setOnClickListener {
            var yazici = binding.akisYaziciDurum.text
            if (yazici == "0"){
                yazici = "1"
            }
            else{
                yazici = "0"
            }
            database.child("users").child(currentuser!!).child("yaziciDurum").setValue(yazici)
        }
        binding.profil.setOnClickListener {
            intent = Intent(applicationContext,Menu::class.java)
            startActivity(intent)
        }
        binding.btncikis.setOnClickListener {
            auth.signOut()
            intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}