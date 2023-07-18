package com.example.firstapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM result")
    fun getUsers(): LiveData<List<com.example.firstapp.response.UserList.Result>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecords(users: List<com.example.firstapp.response.UserList.Result>)


}