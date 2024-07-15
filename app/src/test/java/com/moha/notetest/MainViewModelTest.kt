package com.moha.notetest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.moha.notetest.domain.model.note.Note
import com.moha.notetest.domain.usecase.NoteUseCase
import com.moha.notetest.ui.screen.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MainViewModelTest {

    private lateinit var noteUseCase: NoteUseCase
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        val testDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testDispatcher)

        noteUseCase = mock(NoteUseCase::class.java)
        viewModel = MainViewModel(noteUseCase)
    }

    @Test
    fun testInsert() = runBlocking {
        val note = Note(
            title = "Test Note",
            content = "This is a test note."
        )
        viewModel.insert(note)
        verify(noteUseCase).insert(note)
    }

    @Test
    fun testUpdate() = runBlocking {
        val note = Note(
            id = 1,
            title = "Updated Note",
            content = "This is an updated note."
        )
        viewModel.update(note)
        verify(noteUseCase).update(note)
    }

    @Test
    fun testDelete() = runBlocking {
        val note = Note(
            id = 1,
            title = "Test Note",
            content = "This is a test note."
        )
        viewModel.delete(note)
        verify(noteUseCase).delete(note)
    }

    @Test
    fun testGetAllNotes() {
        val liveData = MutableLiveData<List<Note>>()
        `when`(noteUseCase.getAllNote()).thenReturn(liveData)

        val observer = mock(Observer::class.java) as Observer<List<Note>>
        viewModel.allNotes.observeForever(observer)

        verify(noteUseCase).getAllNote()
    }
}
