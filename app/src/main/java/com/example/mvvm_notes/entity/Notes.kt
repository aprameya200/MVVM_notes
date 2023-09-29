package com.example.mvvm_notes.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mvvm_notes.enums.Priority

@Entity(tableName = "notes")
@TypeConverters(Notes.PriorityConverter::class)
data class Notes(
    @PrimaryKey(autoGenerate = true)
    private var id: Int?,

    private var title: String?,
    private var subtitle: String?,
    private var notes: String?,
    private var date: String?,

    private var priority: Priority
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("priority")
    ) {
    }

    fun getId(): Int? {
        return id
    }

    // Getter method for title
    fun getTitle(): String? {
        return title
    }

    // Getter method for subtitle
    fun getSubtitle(): String? {
        return subtitle
    }

    // Getter method for notes
    fun getNotes(): String? {
        return notes
    }

    // Getter method for date
    fun getDate(): String? {
        return date
    }

    // Getter method for priority
    fun getPriority(): Priority {
        return priority
    }

    class PriorityConverter {
        @TypeConverter
        fun fromPrio(priority: Priority): String {
            return priority.name
        }

        @TypeConverter
        fun toDirection(priority: String): Priority {
            return try {
                enumValueOf(priority) //convert string into its enum value
            } catch (e: IllegalAccessException) {
                Priority.HIGH
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(subtitle)
        parcel.writeString(notes)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Notes> {
        override fun createFromParcel(parcel: Parcel): Notes {
            return Notes(parcel)
        }

        override fun newArray(size: Int): Array<Notes?> {
            return arrayOfNulls(size)
        }
    }
}
