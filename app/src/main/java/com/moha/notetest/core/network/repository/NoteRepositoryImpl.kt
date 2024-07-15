package com.moha.notetest.core.network.repository

import androidx.lifecycle.LiveData
import com.moha.notetest.core.service.db.NoteDao
import com.moha.notetest.domain.model.note.Note
import com.moha.notetest.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    override suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun update(note: Note) {
        noteDao.update(note)
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}