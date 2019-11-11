package com.example.celebritiesapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

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