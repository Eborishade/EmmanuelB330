package com.example.worddictionary.activities.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.worddictionary.R
import com.example.worddictionary.database.WordDatabase
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout


// The fragment is responsible for handling events. These events include responding to
// user input and data changes through observation of view model data changes.
class SearchFragment : Fragment() {

    private val TAG = javaClass.simpleName
    private lateinit var layout: View
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {

        layout = inflater.inflate(R.layout.fragment_search, container, false)
        val application = requireNotNull(activity).application
        val dao = WordDatabase.getInstance(application).wordDatabaseDao
        val viewModelFactory = SearchViewModelFactory(dao)

        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]

        val searchButton = layout.findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener {
            wordSearch()
        }

        // Observe the wordDef so that when a word is found (an exact match of the user input)
        // by the Dictionary API we navigate to the add word screen.
        viewModel.wordDef.observe(viewLifecycleOwner) { word ->
            if (word != null) {
                findNavController()
                    .navigate(
                        SearchFragmentDirections.actionSearchWordFragmentToAddWordFragment(word)
                    )
                Log.d(TAG, "Observed ${viewModel.wordDef.value}")
                // This sets the wordDef property to null so that the observer doesn't get notified
                // again when navigating back to the search screen
                viewModel.resetWordDef()
            }
        }

        // Observe the suggestedWords so that when a list of suggested words is
        // returned by the API we can display this on the search word screen.
        viewModel.suggestedWords.observe(viewLifecycleOwner) { suggestedWords ->
            if (suggestedWords != null) {
                val suggestedText = layout.findViewById<Button>(R.id.suggested_word_textview)
                "Not Found. Suggested: $suggestedWords".also { suggestedText.text = it }
                suggestedText.isVisible = true
            }
        }

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner) {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.database_error_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                viewModel.doneShowingSnackbar()
            }
        }

        return layout
    }

    private fun wordSearch() {
        // search for the word that the user entered
        val searchInput: TextInputLayout = layout.findViewById(R.id.search_input)
        val searchWord = searchInput.editText?.text.toString()

        // The search is performed by the view model, because the view model
        // is responsible for data access
        viewModel.performWordSearch(searchWord)
    }
}