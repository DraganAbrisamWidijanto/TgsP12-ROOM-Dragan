package com.example.tgsp12_crud_dragan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tgsp12_crud_dragan.data.dao.userDAO
import com.example.tgsp12_crud_dragan.data.entity.User

@Database(entities= [User::class], version=1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): userDAO

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}