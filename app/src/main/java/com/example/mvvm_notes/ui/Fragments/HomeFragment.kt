package com.example.mvvm_notes.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm_notes.R
import com.example.mvvm_notes.databinding.ActivityMainBinding
import com.example.mvvm_notes.databinding.FragmentHomeBinding
import com.example.mvvm_notes.databinding.NotesBinding
import com.example.mvvm_notes.ui.Adapter.NotesAdapter
import com.example.mvvm_notes.viewmodel.NotesViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment


        viewModel.getNotes().observe(viewLifecycleOwner) {
            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), it)
        }


        binding.btnAddNotes.setOnClickListener {
//            to naviagate between a navigation graph
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        binding.midTag.setOnClickListener {

        }

        return binding.root

    }


}