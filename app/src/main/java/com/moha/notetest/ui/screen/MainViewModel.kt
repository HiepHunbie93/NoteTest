package com.moha.notetest.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moha.notetest.domain.model.note.Note
import com.moha.notetest.domain.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {
    val allNotes: LiveData<List<Note>> = noteUseCase.getAllNote()

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteUseCase.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteUseCase.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteUseCase.delete(note)
    }
}