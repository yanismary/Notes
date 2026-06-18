package com.neac.userapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "first_name")
    var firstName : String,

    @ColumnInfo(name = "last_name")
    var lastName  : String,
)
