package com.example.worddictionary.activities.addword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.worddictionary.R
import com.example.worddictionary.database.WordDatabase
import com.example.worddictionary.databinding.FragmentAddWordBinding
import com.google.android.material.snackbar.Snackbar

class AddWordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentAddWordBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application).wordDatabaseDao
        binding.lifecycleOwner = this.viewLifecycleOwner

        // Get the word object from the argument passed to it so that it can
        // be passed the view model via the view model factory.
        val word = AddWordFragmentArgs.fromBundle(requireArguments()).wordDef

        val viewModelFactory = AddWordViewModelFactory(word, application)
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[AddWordViewModel::class.java]
        // The ViewModelProvider uses the factory to create the view model.
        binding.viewModel = viewModel

        // Navigates back to the search fragment
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addWordFragment_to_DictWordsFragment)
        }


        binding.addWordButton.setOnClickListener {
            //add word to database then navigate

            viewModel.addWord(dao)
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                getString(R.string.added_to_database_message),
                Snackbar.LENGTH_SHORT
            ).show()
            //findNavController().navigate(R.id.action_addWordFragment_to_searchWordFragment)
        }
        return binding.root
    }

}