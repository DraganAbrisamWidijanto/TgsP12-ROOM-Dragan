package com.example.tgsp12_crud_dragan.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tgsp12_crud_dragan.data.entity.User

// Antarmuka DAO (Data Access Object) untuk entitas "User"
@Dao
interface userDAO {

    // Mengambil semua data pengguna dari tabel "user"
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    // Memuat data pengguna berdasarkan array ID yang diberikan
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    // Memasukkan satu atau beberapa pengguna ke dalam tabel
    @Insert
    fun insertAll(vararg users: User)

    // Menghapus pengguna tertentu dari tabel
    @Delete
    fun delete(user: User)

    // Mencari pengguna berdasarkan ID tertentu
    @Query("SELECT * FROM user WHERE uid = :uid")
    fun findById(uid: Int): User

    // Memperbarui data pengguna di dalam tabel
    @Update
    fun update(user: User)
}