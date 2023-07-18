package com.example.firstapp.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [com.example.firstapp.response.UserList.Result::class], version = 1)
@TypeConverters(Converts::class)
abstract class UsersDataBase:RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        private var DB_INSTANCE: UsersDataBase? = null

        fun getUserDbInstance(context: Context): UsersDataBase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDataBase::class.java,
                    "User_Db"
                )
                    .build()
            }
            Log.d("logging", "DB created")
            return DB_INSTANCE!!
        }
    }
}