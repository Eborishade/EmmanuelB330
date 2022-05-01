package com.example.worddictionary.activities.words

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.worddictionary.R
import com.example.worddictionary.database.WordDatabase
import com.example.worddictionary.databinding.FragmentWordDictBinding

class WordDictFragment : Fragment() {

    private lateinit var viewModel: WordDictViewModel

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
        binding.wordsList.adapter = WordDictAdapter()

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