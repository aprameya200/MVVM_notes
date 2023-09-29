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

}