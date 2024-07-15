package com.moha.notetest.domain.repository

import androidx.lifecycle.LiveData
import com.moha.notetest.domain.model.note.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    val allNotes: LiveData<List<Note>>

    suspend fun insert(note: Note)

    suspend fun update(note: Note)

    suspend fun delete(note: Note)
}