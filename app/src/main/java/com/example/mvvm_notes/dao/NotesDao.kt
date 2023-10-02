package com.example.mvvm_notes.dao

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mvvm_notes.entity.Notes
import com.example.mvvm_notes.enums.Priority

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getNotes() : LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)

    @Query("SELECT * FROM notes WHERE priority = :high")
    fun getHighPriorityNotes(high: Priority) : LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority = :medium")
    fun getMediumPriorityNotes(medium: Priority) : LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority = :low")
    fun getLowPriorityNotes(low: Priority) : LiveData<List<Notes>>

}