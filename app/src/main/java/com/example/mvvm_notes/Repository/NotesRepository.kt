package com.example.mvvm_notes.Repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mvvm_notes.dao.NotesDao
import com.example.mvvm_notes.entity.Notes
import com.example.mvvm_notes.enums.Priority

class NotesRepository(val dao: NotesDao) {

    fun getNotes(): LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id : Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

    fun getHighPriorityNotes(priority: Priority): LiveData<List<Notes>>{
        return dao.getHighPriorityNotes(priority)
    }

    fun getMediumPriorityNotes(priority: Priority): LiveData<List<Notes>>{
        return dao.getMediumPriorityNotes(priority)
    }

    fun getLowPeiorityNotes(priority: Priority): LiveData<List<Notes>>{
        return dao.getLowPriorityNotes(priority)
    }

}