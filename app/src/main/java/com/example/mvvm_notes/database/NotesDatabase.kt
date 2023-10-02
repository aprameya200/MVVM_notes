package com.example.mvvm_notes.database

import android.content.Context
import androidx.room.*
import com.example.mvvm_notes.dao.NotesDao
import com.example.mvvm_notes.entity.Notes

@Database(entities = [Notes::class], version = 2)
@TypeConverters(Notes.PriorityConverter::class) // Add your TypeConverter here
abstract class NotesDatabase : RoomDatabase(){

    abstract fun getNotesDao() : NotesDao

    companion object{
        @Volatile
        var INSTANCE : NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase{ //get the current context
            var tempInstance = INSTANCE

            tempInstance?.let{
                return tempInstance
            }
            synchronized(this){
                val dbBuilder = Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()
                INSTANCE = dbBuilder
                return dbBuilder
            }
        }
    }
}