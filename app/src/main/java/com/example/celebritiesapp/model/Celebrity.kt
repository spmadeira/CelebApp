package com.example.celebritiesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "celebrity")
data class Celebrity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name="name")
    var name: String,

    @ColumnInfo(name="fotopath")
    var fotoPath: String,

    @ColumnInfo(name = "quotes")
    var quotes: String
)