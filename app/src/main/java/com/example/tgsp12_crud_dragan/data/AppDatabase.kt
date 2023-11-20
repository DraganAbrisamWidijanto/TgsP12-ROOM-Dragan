package com.example.tgsp12_crud_dragan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tgsp12_crud_dragan.data.dao.userDAO
import com.example.tgsp12_crud_dragan.data.entity.User

// Kelas Database yang mengelola entitas "User"
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Metode abstrak untuk mendapatkan objek DAO untuk data pengguna
    abstract fun userDao(): userDAO

    companion object {
        // Instance database yang hanya dibuat sekali (singleton)
        private var instance: AppDatabase? = null

        // Fungsi untuk mendapatkan atau membuat instance database
        fun getInstance(context: Context): AppDatabase {
            // Jika instance belum ada, buat instance baru
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
                    // Menerapkan migrasi yang menghapus semua data saat ada perubahan versi
                    .fallbackToDestructiveMigration()
                    // Mengizinkan operasi database di utas utama
                    .allowMainThreadQueries()
                    // Membangun dan mengembalikan instance database
                    .build()
            }
            // Mengembalikan instance database yang sudah ada atau yang baru dibuat
            return instance!!
        }
    }
}
