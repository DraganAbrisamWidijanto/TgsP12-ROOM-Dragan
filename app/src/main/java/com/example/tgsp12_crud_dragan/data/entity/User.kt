package com.example.tgsp12_crud_dragan.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Mendefinisikan entitas (tabel) "User" yang akan disimpan dalam database
@Entity
data class User(
    // Kolom ID utama yang akan di-generate otomatis oleh database
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,

    // Kolom untuk menyimpan nama lengkap pengguna
    @ColumnInfo(name = "full_name") var fullName: String,

    // Kolom untuk menyimpan alamat email pengguna (opsional)
    @ColumnInfo(name = "email") var email: String?,

    // Kolom untuk menyimpan nomor telepon pengguna (opsional)
    @ColumnInfo(name = "phone_number") var phoneNumber: String?
)
