package com.example.mvvm_notes.ui.Fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mvvm_notes.R
import com.example.mvvm_notes.databinding.FragmentEditNotesBinding
import com.example.mvvm_notes.entity.Notes
import com.example.mvvm_notes.enums.Priority
import com.example.mvvm_notes.viewmodel.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate

class EditNotesFragment : Fragment() {


    lateinit var binding: FragmentEditNotesBinding

    val notes by navArgs<EditNotesFragmentArgs>()
    val viewModel: NotesViewModel by viewModels()

    var priority: Priority = Priority.HIGH


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        binding.title.setText(notes.data.getTitle())
        binding.subtitle.setText(notes.data.getSubtitle())
        binding.notes.setText(notes.data.getNotes())

        when (notes.data.getPriority()) {
            Priority.HIGH -> binding.redTickBtn.setImageResource(R.drawable.baseline_done_24)
            Priority.MEDIUM -> binding.yellowTickBtn.setImageResource(R.drawable.baseline_done_24)
            Priority.LOW -> binding.greenTickBtn.setImageResource(R.drawable.baseline_done_24)
        }

        binding.redTickBtn.setOnClickListener { updatePriority(Priority.HIGH, binding.redTickBtn) }
        binding.greenTickBtn.setOnClickListener {
            updatePriority(
                Priority.LOW,
                binding.greenTickBtn
            )
        }
        binding.yellowTickBtn.setOnClickListener {
            updatePriority(
                Priority.MEDIUM,
                binding.yellowTickBtn
            )
        }

        binding.editNotes.setOnClickListener { updateNotes(it) }



        return binding.root
    }

    private fun updateNotes(it: View?) {
        viewModel.updateNotes(
            Notes(
                notes.data.getId(),
                binding.title.text.toString(),
                binding.subtitle.text.toString(),
                binding.notes.text.toString(),
                LocalDate.now().toString(),
                priority
            )
        )

        Toast.makeText(context, "Note Updated Succesfully", Toast.LENGTH_LONG)

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    fun updatePriority(priority: Priority, btn: FloatingActionButton) {

        this.priority = priority

        var btns = listOf(binding.redTickBtn, binding.greenTickBtn, binding.yellowTickBtn)

        //if btn selected
        btns.forEach { button ->
            button.setImageResource(if (button == btn) R.drawable.baseline_done_24 else 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var id = notes.data.getId()

        if (item.itemId == R.id.deleteIcon) {

            if (id != null) {
                viewModel.deleteNotes(id)
            }
        }

        Toast.makeText(context, "Note Deleted Succesfully", Toast.LENGTH_LONG)

        return super.onOptionsItemSelected(item)
    }


}