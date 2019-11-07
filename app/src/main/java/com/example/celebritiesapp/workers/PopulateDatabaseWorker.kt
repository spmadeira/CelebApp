package com.example.celebritiesapp.workers

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.celebritiesapp.database.AppDatabase
import com.example.celebritiesapp.model.Celebrity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class PopulateDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope{
        try{
            applicationContext.assets.open("celebs.json").use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Celebrity>>() {}.type
                    val plantList: List<Celebrity> = Gson().fromJson(jsonReader, plantType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.celebDao().insertAll(plantList)

                    Result.success()
                }
            }
        } catch(ex: Exception){
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }
}