package com.example.firstapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.firstapp.response.UserList.Location
import com.example.firstapp.response.UserList.Origin

class Converts {
@TypeConverter
    fun fromOrigin(origin: Origin):String {
        return origin.name.toString()
    }
    @TypeConverter
    fun toOrigin(name: String): Origin {
        return Origin(name, name)
    }


    @TypeConverter
    fun fromLocation(location: Location):String {
        return location.name.toString()
    }
    @TypeConverter
    fun toLocation(name: String): Location {
        return Location(name, name)
    }
}