package com.moha.notetest.domain.usecase

import androidx.lifecycle.LiveData
import com.moha.notetest.core.network.repository.NoteRepositoryImpl
import com.moha.notetest.domain.model.note.Note
import javax.inject.Inject

class NoteUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl
) {
    fun getAllNote(): LiveData<List<Note>> {
        return noteRepositoryImpl.allNotes
    }

    suspend fun insert(note: Note) {
        noteRepositoryImpl.insert(note)
    }

    suspend fun update(note: Note) {
        noteRepositoryImpl.update(note)
    }

    suspend fun delete(note: Note) {
        noteRepositoryImpl.delete(note)
    }
}