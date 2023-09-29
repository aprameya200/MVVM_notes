package com.example.mvvm_notes.ui.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_notes.R
import com.example.mvvm_notes.entity.Notes
import com.example.mvvm_notes.enums.Priority
import com.example.mvvm_notes.ui.Fragments.EditNotesFragment
import com.example.mvvm_notes.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, private val notes: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.titleNotes)
        var notes = itemView.findViewById<TextView>(R.id.descriptionNotes)
        var date = itemView.findViewById<TextView>(R.id.dateNotes)

        //      buttons
        var dot = itemView.findViewById<View>(R.id.dot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var passData = notes[position]
        holder.title.text = notes[position].getTitle()
        holder.notes.text = notes[position].getNotes()
        holder.date.text = notes[position].getDate()

        when (notes[position].getPriority()) {
            Priority.HIGH -> holder.dot.setBackgroundResource(R.drawable.red_dot)
            Priority.LOW -> holder.dot.setBackgroundResource(R.drawable.green_dot)
            Priority.MEDIUM -> holder.dot.setBackgroundResource(R.drawable.yellow_dot)
        }

        holder.itemView.setOnClickListener {
            var action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(passData)
            Navigation.findNavController(it).navigate(action)

        }

    }

}