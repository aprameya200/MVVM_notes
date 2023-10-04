package com.example.mvvm_notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvm_notes.Repository.NotesRepository
import com.example.mvvm_notes.database.NotesDatabase
import com.example.mvvm_notes.entity.Notes
import com.example.mvvm_notes.enums.Priority

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

    fun getNotesByPriority(priority: Priority): LiveData<List<Notes>>{
        when (priority) {
            Priority.HIGH -> return repository.getHighPriorityNotes(priority)
            Priority.MEDIUM -> return repository.getMediumPriorityNotes(priority)
            Priority.LOW -> return repository.getLowPeiorityNotes(priority)
            Priority.ALL -> return repository.getNotes()
        }
    }

    fun toggleFilter(priority: Priority,notesPriority: Priority): Priority {

        if (notesPriority == priority) {
            return Priority.ALL
        } else {
            return priority
        }

    }

    fun highPriorityNotes(priority: Priority): LiveData<List<Notes>>{
        return repository.getHighPriorityNotes(priority)
    }

    fun mediumPriorityNotes(priority: Priority): LiveData<List<Notes>>{
        return repository.getMediumPriorityNotes(priority)
    }

    fun lowPriorityNotes(priority: Priority): LiveData<List<Notes>>{
        return repository.getLowPeiorityNotes(priority)
    }
}