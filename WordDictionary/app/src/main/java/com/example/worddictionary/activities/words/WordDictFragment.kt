package com.example.worddictionary.activities.words

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.worddictionary.R
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabase
import com.example.worddictionary.databinding.FragmentWordDictBinding
import com.google.android.material.snackbar.Snackbar

class WordDictFragment : Fragment() {

    private lateinit var viewModel: WordDictViewModel
    private lateinit var activeList: List<Word>
    private lateinit var inactiveList: List<Word>
    private lateinit var allWordList: List<Word>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val binding = FragmentWordDictBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val dao = WordDatabase.getInstance(application).wordDatabaseDao

        val viewModelFactory = WordDictViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[WordDictViewModel::class.java]

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Adapter & Element active/inactive ClickListener
        val adapter = WordDictAdapter(WordDictAdapter.OnClickListener {
            viewModel.toggleActive(it)

            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                getString(R.string.toggle_active_message),
                Snackbar.LENGTH_SHORT).show()
        })
        binding.wordsList.adapter = adapter

        // Database Observers
        viewModel.activeWords.observe(viewLifecycleOwner) {
            if (it != null) {
                activeList = it
            }
        }
        viewModel.inactiveWords.observe(viewLifecycleOwner) {
            if (it != null) {
                inactiveList = it
            }
        }
        viewModel.allWords.observe(viewLifecycleOwner) {
            if (it != null) {
                allWordList = it
            }
        }

        // Menu filter Observer -runtime note: must select menu option for data to display
        viewModel.filter.observe(viewLifecycleOwner) {
            adapter.submitList(
                when (it) {
                    "show_active" -> activeList
                    "show_inactive" -> inactiveList
                    else -> allWordList
                }
            )
        }

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_word_menu) {
            findNavController()
                .navigate(WordDictFragmentDirections.actionDictWordsFragmentToSearchWordFragment())

        } else if (item.itemId == R.id.clear_database_menu) {
            viewModel.clearData()

        } else {
            viewModel.changeFilter(
                when (item.itemId) {
                    R.id.show_active_menu -> "show_active"
                    R.id.show_inactive_menu -> "show_inactive"
                    else -> "show_all"
                }
            )
        }
        return true
    }

}