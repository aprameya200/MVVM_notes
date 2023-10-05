package com.example.mvvm_notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_notes.Repository.NotesRepository
import com.example.mvvm_notes.database.NotesDatabase
import com.example.mvvm_notes.entity.Notes
import com.example.mvvm_notes.enums.Priority

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: NotesRepository
    var notesLD = MutableLiveData<List<Notes>>()

    var prio = Priority.ALL

    init { //runs when the Class is first initialized
        val dao = NotesDatabase.getDatabase(application).getNotesDao()
        repository = NotesRepository(dao)
// Initialize with an empty list
        val notesLiveData = getNotesByPriority(prio)

        //observ livedata
        notesLiveData.observeForever { newNotesList ->
            // Update your LiveData with the new value
            notesLD.postValue(newNotesList)
        }

    }

    fun insertNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }

    fun getNotesByPriority(priority: Priority): LiveData<List<Notes>> {
//        updateNotesList(repository.getNotes())

        when (priority) {
            Priority.HIGH -> return repository.getHighPriorityNotes(priority)
            Priority.MEDIUM -> return repository.getMediumPriorityNotes(priority)
            Priority.LOW -> return repository.getLowPeiorityNotes(priority)
            Priority.ALL -> return repository.getNotes()
        }

    }

    fun toggleFilter(priority: Priority, notesPriority: Priority) : Priority {

        if (notesPriority == priority) {
            prio = Priority.ALL
        } else {
            prio = priority
        }

        val notesLiveData = getNotesByPriority(prio)

        //observ livedata
        notesLiveData.observeForever { newNotesList ->
            // Update your LiveData with the new value
            notesLD.postValue(newNotesList)
        }

        return prio
    }

}