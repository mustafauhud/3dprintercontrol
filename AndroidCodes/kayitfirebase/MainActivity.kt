package com.example.kayitfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kayitfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var auth = FirebaseAuth.getInstance()

        binding.btnGiris.setOnClickListener {
            var email:String = binding.girisMail.text.toString()
            var sifre:String = binding.girisSifre.text.toString()

            if (email.equals("") || sifre.equals("")){
                Toast.makeText(applicationContext, "Mail veya sifre bos", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,sifre).addOnSuccessListener {
                    intent = Intent(applicationContext,Menu::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(applicationContext, "Mail veya sifre Yapılamadı", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnHesapYok.setOnClickListener {
            intent = Intent(applicationContext,KayitOl::class.java)
            startActivity(intent)
        }

    }
}