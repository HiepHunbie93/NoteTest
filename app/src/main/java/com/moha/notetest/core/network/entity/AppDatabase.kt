package com.moha.notetest.core.network.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moha.notetest.core.service.db.NoteDao
import com.moha.notetest.domain.model.note.Note

@Database(entities = [
    Note::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
