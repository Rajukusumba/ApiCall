package com.example.firstapp.response.UserList


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "result"
)
data class Result(

    @PrimaryKey(autoGenerate = true)
    val Rid: Int? = null,
    @SerializedName("created")
    val created: String? = "",
//    @SerializedName("episode")
//    val episode: List<String?>? = listOf(),
    @SerializedName("gender")
    val gender: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("location")
    val location: Location? = Location(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("origin")
    val origin: Origin? = Origin(),
    @SerializedName("species")
    val species: String? = "",
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("url")
    val url: String? = ""
)