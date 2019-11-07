package com.example.celebritiesapp.dao

import androidx.room.*
import com.example.celebritiesapp.model.Celebrity

@Dao
interface CelebrityDao{
    @Query("SELECT * FROM celebrity")
    fun getAll(): List<Celebrity>

    @Query("SELECT * FROM celebrity WHERE id LIKE :id")
    fun findById(id: Int): Celebrity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(celebs: List<Celebrity>)

    @Delete
    fun delete(celeb: Celebrity)

    @Update
    fun updateCeleb(vararg celebs: Celebrity)
}