package com.example.celebritiesapp.database

import android.content.ContentValues.TAG
import android.content.Context
import android.nfc.Tag
import android.util.Log
import androidx.room.Database
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.celebritiesapp.dao.CelebrityDao
import com.example.celebritiesapp.model.Celebrity
import com.example.celebritiesapp.workers.PopulateDatabaseWorker
import java.lang.Exception

@Database(entities = arrayOf(Celebrity::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun celebDao(): CelebrityDao

    companion object {
        @Volatile
        private var instance : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase{
            return Room.databaseBuilder(context, AppDatabase::class.java, "celebrities-db")
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase){
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<PopulateDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .allowMainThreadQueries()
                .build()
        }
    }
}