package com.example.celebritiesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.celebritiesapp.R
import com.example.celebritiesapp.adapter.CelebritiesAdapter
import com.example.celebritiesapp.adapter.CelebrityClickListener
import com.example.celebritiesapp.database.AppDatabase

class MainActivity : AppCompatActivity(), CelebrityClickListener {

    private lateinit var celebsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celebsList = findViewById(R.id.main_recyclerview_celebs_list)

        celebsList.layoutManager = LinearLayoutManager(this)

        val adapter = CelebritiesAdapter(this, AppDatabase.getCelebrities(this), this)
        celebsList.adapter = adapter
    }

    override fun onClick(view: View, index: Int) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("celebrity_index",index)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,view,AppDatabase.getCelebrities(this)[index].name)

        startActivity(intent, options.toBundle())
    }
}
