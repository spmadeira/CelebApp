package com.example.celebritiesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.celebritiesapp.R
import com.example.celebritiesapp.database.AppDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = AppDatabase.getInstance(applicationContext)
        val celebs = database.celebDao().getAll()

        Log.i("coisa","aaa")

        for (i in 0..celebs.size-1){
            Log.i("coisa", celebs[i].name + " " + celebs[i].quotes)
        }
    }
}
