package com.example.mvvm_notes.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.mvvm_notes.R
import com.example.mvvm_notes.databinding.FragmentCreateNoteBinding
import com.example.mvvm_notes.entity.Notes
import com.example.mvvm_notes.enums.Priority
import com.example.mvvm_notes.viewmodel.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.util.Date

class CreateNoteFragment : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var notePriority: Priority = Priority.LOW

    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        binding.greenTickBtn.setImageResource(R.drawable.baseline_done_24)

        binding.btnAddNotes.setOnClickListener {
            addNotes(it) //it means the btn
        }

        binding.redTickBtn.setOnClickListener { updatePriority(Priority.HIGH, binding.redTickBtn) }
        binding.greenTickBtn.setOnClickListener { updatePriority(Priority.LOW, binding.greenTickBtn) }
        binding.yellowTickBtn.setOnClickListener { updatePriority(Priority.MEDIUM, binding.yellowTickBtn) }

        // Inflate the layout for this fragment
        return binding.root

    }

    private fun updatePriority(priority: Priority, selectedButton: FloatingActionButton) {
        notePriority = priority

        val buttons = listOf(binding.redTickBtn, binding.greenTickBtn, binding.yellowTickBtn)
        buttons.forEach { button ->
            button.setImageResource(if (button == selectedButton) R.drawable.baseline_done_24 else 0)
        }
    }

    private fun addNotes(it: View) {
        val title = binding.title.text.toString()
        val subtitle = binding.subtitle.text.toString()
        val notes = binding.notes.text.toString()

        viewModel.insertNotes(
            Notes(
                null,
                title,
                subtitle,
                notes,
                LocalDate.now().toString(),
                notePriority
            )
        )

        val context = requireContext()
        Toast.makeText(context, "Note added succesfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
    }

}