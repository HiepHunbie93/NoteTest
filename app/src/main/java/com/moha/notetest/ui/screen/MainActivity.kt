package com.moha.notetest.ui.screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moha.notetest.R
import com.moha.notetest.databinding.ActivityMainBinding
import com.moha.notetest.domain.model.note.Note
import com.moha.notetest.ui.theme.NoteTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val adapter = ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, mutableListOf())
        binding.listView.adapter = adapter

        mainViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let {
                adapter.clear()
                adapter.addAll(it)
            }
        })

        binding.fab.setOnClickListener {
            showNoteDialog()
        }

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val note = adapter.getItem(position)
            note?.let {
                showNoteDialog(note)
            }
        }

        binding.listView.setOnItemLongClickListener { _, _, position, _ ->
            val note = adapter.getItem(position)
            note?.let {
                showDeleteConfirmDialog(note)
            }
            true
        }
    }

    private fun showNoteDialog(note: Note? = null) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_note, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val contentEditText = dialogView.findViewById<EditText>(R.id.editTextContent)

        note?.let {
            titleEditText.setText(it.title)
            contentEditText.setText(it.content)
        }

        AlertDialog.Builder(this)
            .setTitle(if (note == null) "Create Note" else "Edit Note")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val title = titleEditText.text.toString()
                val content = contentEditText.text.toString()

                if (note == null) {
                    mainViewModel.insert(Note(title = title, content = content))
                } else {
                    mainViewModel.update(note.copy(title = title, content = content))
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDeleteConfirmDialog(note: Note) {
        AlertDialog.Builder(this)
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Yes") { _, _ ->
                mainViewModel.delete(note)
            }
            .setNegativeButton("No", null)
            .show()
    }
}