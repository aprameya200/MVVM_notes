package com.example.mvvm_notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvm_notes.Repository.NotesRepository
import com.example.mvvm_notes.database.NotesDatabase
import com.example.mvvm_notes.entity.Notes

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: NotesRepository

    init { //runs when the Class is first initialized
        val dao = NotesDatabase.getDatabase(application).getNotesDao()
        repository = NotesRepository(dao)
    }

    fun getNotes(): LiveData<List<Notes>> {
        return repository.getNotes()
    }

    fun insertNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun deleteNotes(id : Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}