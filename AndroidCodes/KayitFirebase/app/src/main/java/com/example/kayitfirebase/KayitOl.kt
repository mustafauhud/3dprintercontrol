package com.example.kayitfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kayitfirebase.databinding.ActivityKayitOlBinding
import com.example.kayitfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class KayitOl : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityKayitOlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var auth = FirebaseAuth.getInstance()
        var database = Firebase.database.reference

        binding.btnKayit.setOnClickListener {
            var email:String = binding.kayitMail.text.toString()
            var sifre:String = binding.kayitSifre.text.toString()
            var kullaniciadi:String = binding.kayitkullaniciadi.text.toString()
            var yaziciadi:String = binding.kayityaziciadi.text.toString()

            var table_temperature:String = null.toString()
            var motor_konum:String = null.toString()
            var nozzle_sicaklik_ayar:String = null.toString()
            var nozzle_temperature:String = null.toString()
            var tabla_sicaklik_ayar:String = null.toString()


            if (email.equals("") || sifre.equals("") || kullaniciadi.equals("") || yaziciadi.equals("")){
                Toast.makeText(applicationContext, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email,sifre).addOnSuccessListener {



                    val user = User(
                        motor_konum,nozzle_sicaklik_ayar,nozzle_temperature,tabla_sicaklik_ayar,table_temperature,kullaniciadi,yaziciadi
                    )



                    val currentuser = FirebaseAuth.getInstance().uid

                    database.child("users").child(currentuser!!).setValue(user)

                    Toast.makeText(applicationContext, "Kayit Basarili", Toast.LENGTH_SHORT).show()
                    intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnAnaSayfa.setOnClickListener {
            intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}