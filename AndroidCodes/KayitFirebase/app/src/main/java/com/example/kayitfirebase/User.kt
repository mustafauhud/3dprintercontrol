package com.example.kayitfirebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(

                val motor_konum: String? = null,
                val nozzle_sicaklik_ayar: String? = null,
                val nozzle_temperature: String? = null,
                val tabla_sicaklik_ayar: String? = null,
                val table_temperature: String? = null,
                val kullaniciadi:String?=null,
                val yaziciadi:String?=null

) {

}