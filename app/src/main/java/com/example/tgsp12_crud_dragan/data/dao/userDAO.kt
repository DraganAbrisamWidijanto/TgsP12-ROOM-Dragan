package com.example.tgsp12_crud_dragan.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tgsp12_crud_dragan.data.entity.User

@Dao
interface userDAO {

@Query("SELECT * FROM user")
fun getAll(): List<User>

@Query("SELECT * FROM user WHERE uid IN (:userIds)")
fun loadAllByIds(userIds: IntArray): List<User>

@Insert
fun insertAll(vararg users: User)

@Delete
fun delete(user: User)

@Query("SELECT * FROM user WHERE uid = :uid")
fun findById(uid: Int): User

@Update
fun update(user: User)
}