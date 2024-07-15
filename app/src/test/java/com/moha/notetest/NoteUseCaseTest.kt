package com.moha.notetest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.moha.notetest.core.network.repository.NoteRepositoryImpl
import com.moha.notetest.core.service.db.NoteDao
import com.moha.notetest.domain.model.note.Note
import com.moha.notetest.domain.usecase.NoteUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class NoteUseCaseTest {
    private lateinit var noteDao: NoteDao
    private lateinit var noteRepositoryImpl: NoteRepositoryImpl
    private lateinit var noteUseCase: NoteUseCase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        noteDao = mock(NoteDao::class.java)
        noteRepositoryImpl = NoteRepositoryImpl(noteDao)
        noteUseCase = NoteUseCase(noteRepositoryImpl)
    }

    @Test
    fun testInsert() = runBlocking {
        val note = Note(
            title = "Test Note",
            content = "This is a test note."
        )
        noteUseCase.insert(note)
        verify(noteDao).insert(note)
    }

    @Test
    fun testUpdate() = runBlocking {
        val note = Note(
            id = 1,
            title = "Updated Note",
            content = "This is an updated note."
        )
        noteUseCase.update(note)
        verify(noteDao).update(note)
    }

    @Test
    fun testDelete() = runBlocking {
        val note = Note(
            id = 1,
            title = "Test Note",
            content = "This is a test note."
        )
        noteUseCase.delete(note)
        verify(noteDao).delete(note)
    }

    @Test
    fun testGetAllNotes() {
        val liveData = MutableLiveData<List<Note>>()
        `when`(noteDao.getAllNotes()).thenReturn(liveData)

        val observer = mock(Observer::class.java) as Observer<List<Note>>
        noteUseCase.getAllNote().observeForever(observer)

        verify(noteDao).getAllNotes()
    }
}