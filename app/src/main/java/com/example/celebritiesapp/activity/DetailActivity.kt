package com.example.celebritiesapp.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.celebritiesapp.R
import com.example.celebritiesapp.database.AppDatabase

class DetailActivity : AppCompatActivity(){
    private lateinit var celebrityImage: ImageView
    private lateinit var celebrityQuote: TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var index: Int = intent.getIntExtra("celebrity_index", 0)
        //val celebrity = AppDatabase.getInstance(this).celebDao().findById(index)
        val celebrity = AppDatabase.getCelebrities(this)[index]
        celebrityImage = findViewById(R.id.detail_imageview_celebrity)
        celebrityImage.setImageResource(resources.getIdentifier(celebrity.fotoPath, "drawable", packageName))
        celebrityImage.transitionName = celebrity.name
        celebrityQuote = findViewById(R.id.detail_textview_description)
        val quotes = celebrity.quotes.split(';')
        val quote = quotes.random()
        celebrityQuote.text = quote
    }
}