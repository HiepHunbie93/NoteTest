package com.moha.notetest.core

import android.app.Application
import android.content.Context
import com.moha.notetest.core.network.entity.AppDatabase
import com.moha.notetest.core.service.db.NoteDao
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltAndroidApp
class MyApplication @Inject constructor() : Application() {

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var noteDao: NoteDao

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            initializeDatabase(this@MyApplication)
        }
    }


    private suspend fun initializeDatabase(context: Context) = withContext(Dispatchers.IO) {
        // initializeDatabaseIfNeeded
    }
}