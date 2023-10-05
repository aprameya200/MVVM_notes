package com.example.mvvm_notes.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm_notes.R
import com.example.mvvm_notes.databinding.FragmentHomeBinding
import com.example.mvvm_notes.entity.Notes
import com.example.mvvm_notes.enums.Priority
import com.example.mvvm_notes.ui.Adapter.NotesAdapter
import com.example.mvvm_notes.viewmodel.NotesViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()

    var notesPriority: Priority = Priority.ALL

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        initUI()
        return binding.root
    }

    fun initUI() {

        viewModel.notesLD.observe(viewLifecycleOwner){
            inflateLayout(it)
        }

        setToggleListeners()

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

    }

    private fun setToggleListeners() {

        var buttons = listOf<TextView>(binding.highTag, binding.lowTag, binding.midTag)

        buttons.forEach { tag ->
            tag.setOnClickListener {

                var priority = when (tag) {
                    binding.highTag -> Priority.HIGH
                    binding.midTag -> Priority.MEDIUM
                    binding.lowTag -> Priority.LOW
                    else -> Priority.ALL
                }


                notesPriority = viewModel.toggleFilter(priority,notesPriority)
            }
        }
    }


    fun inflateLayout(notes: List<Notes>) {
        binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notes)
    }

}