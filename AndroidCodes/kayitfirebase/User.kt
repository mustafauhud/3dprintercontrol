package com.example.kayitfirebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val email: String? = null,
                val sifre:String? = null,
                val yaziciDurum: Int? = null
) {

}