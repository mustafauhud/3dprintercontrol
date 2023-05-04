//package com.example.kayitfirebase
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.kayitfirebase.R
//import com.example.kayitfirebase.databinding.FragmentAramaBinding
//import com.google.firebase.auth.FirebaseAuth
//
//
//class AramaFragment : Fragment() {
//    private lateinit var binding: FragmentAramaBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        var auth = FirebaseAuth.getInstance()
//        auth.signOut()
//
//        val intent = Intent(activity,MainActivity::class.java)
//        startActivity(intent)
//        getActivity()?.finishAffinity()
//    }
//}

//override fun onCreateView(
//    inflater: LayoutInflater, container: ViewGroup?,
//    savedInstanceState: Bundle?
//): View? {
//    binding = FragmentAramaBinding.inflate(inflater,container,false)
//    return binding.root
//}