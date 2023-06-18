package com.example.kayitfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.kayitfirebase.databinding.ActivityMenuBinding
import com.google.firebase.auth.FirebaseAuth

class Menu : AppCompatActivity() {
    lateinit var binding : ActivityMenuBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        var auth = FirebaseAuth.getInstance()
        setContentView(binding.root)



        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(1).isEnabled = false

        navController = Navigation.findNavController(this,R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(binding.bottomNavView,navController)

        binding.cikis.setOnClickListener{

//            navController.popBackStack()
//            navController.navigate(R.id.aramaFragment)

            auth.signOut()
            intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}