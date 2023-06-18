package com.example.kayitfirebase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.example.kayitfirebase.R
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

//                var nozzle_temperature = snapshot.child("users").child(currentuser!!).child("nozzle_temperature").getValue()
//                var table_temperature = snapshot.child("users").child(currentuser!!).child("table_temperature").getValue()
//
//                binding.nozzleTemperature.text = nozzle_temperature.toString()
//                binding.tableTemperature.text = table_temperature.toString()


                var nozzle_sicaklik_ayar_fbcek = snapshot.child("users").child(currentuser!!).child("nozzle_sicaklik_ayar").getValue()
                var a = nozzle_sicaklik_ayar_fbcek.toString()
                var b = a.drop(7)
                var nozzle_ayarlanan_sicaklik = b.dropLast(1)


                binding.nozzleSicaklikAyar.text = nozzle_ayarlanan_sicaklik.toString()

                var tabla_sicaklik_ayar_fbcek = snapshot.child("users").child(currentuser!!).child("tabla_sicaklik_ayar").getValue()
                var c = tabla_sicaklik_ayar_fbcek.toString()
                var d = c.drop(7)
                var tabla_ayarlanan_sicaklik = d.dropLast(1)

                binding.tablaSicaklikAyar.text = tabla_ayarlanan_sicaklik.toString()

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

        // nozzle sıcaklık ayarlama
        binding.nozzleSicaklikIsteButton.setOnClickListener {
            var nozzlesicakliksorgu = binding.nozzleSicaklikIste.text.toString().toIntOrNull()
            var nozzlemaxsicaklik = 300
            var nozzleminsicaklik = 0
            if (nozzlesicakliksorgu != null) {
                if (nozzlesicakliksorgu > nozzlemaxsicaklik || nozzlesicakliksorgu < nozzleminsicaklik) {
                    Toast.makeText(activity, "Nozzle sıcaklığı 0°C ile 300 °C arasında olmalıdır", Toast.LENGTH_SHORT).show()
                    binding.nozzleSicaklikIste.text.clear()
                }else{
                    var nozzleistenensicaklik = binding.nozzleSicaklikIste.text.toString()
                    var nozzlesicaklikayar = "M109 S<"+nozzleistenensicaklik+">"

                    binding.nozzleSicaklikIste.onEditorAction(EditorInfo.IME_ACTION_DONE)// butona tıklayınca klavyeyi kapat
                    binding.nozzleSicaklikIste.text.clear()

                    database.child("users").child(currentuser!!).child("nozzle_sicaklik_ayar").setValue(nozzlesicaklikayar)
                }
            }
            else{
                Toast.makeText(activity, "Nozzle sıcaklığı 0°C ile 300 °C arasında olmalıdır", Toast.LENGTH_SHORT).show()
                binding.nozzleSicaklikIste.text.clear()
            }
        }

        // tabla sıcaklık ayarlama
        binding.tablaSicaklikIsteButton.setOnClickListener {
            var tablasicakliksorgu = binding.tablaSicaklikIste.text.toString().toIntOrNull()
            var tablamaxsicaklik = 80
            var tablaminsicaklik = 0

            if (tablasicakliksorgu != null){
                if (tablasicakliksorgu > tablamaxsicaklik || tablasicakliksorgu < tablaminsicaklik){
                    Toast.makeText(activity, "Tabla sıcaklığı 0°C ile 80 °C arasında olmalıdır", Toast.LENGTH_SHORT).show()
                    binding.tablaSicaklikIste.text.clear()
                }else{
                    var tablaistenensicaklik = binding.tablaSicaklikIste.text.toString()
                    var tablasicaklikayar = "M140 S<"+tablaistenensicaklik+">"

                    binding.tablaSicaklikIste.onEditorAction(EditorInfo.IME_ACTION_DONE)// butona tıklayınca klavyeyi kapat
                    binding.tablaSicaklikIste.text.clear()

                    database.child("users").child(currentuser!!).child("tabla_sicaklik_ayar").setValue(tablasicaklikayar)
                }
            }else{
                Toast.makeText(activity, "Tabla sıcaklığı 0°C ile 80 °C arasında olmalıdır", Toast.LENGTH_SHORT).show()
                binding.tablaSicaklikIste.text.clear()
            }
        }

        // motor konum ayarlama
        binding.motorKonumAyarlaButton.setOnClickListener {
            var motorkonumdeger = "G28"
            database.child("users").child(currentuser!!).child("motor_konum").setValue(motorkonumdeger)
        }

        return binding.root
    }
}