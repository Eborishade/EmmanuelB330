package com.example.worddictionary.activities.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worddictionary.api.DictionaryApi
import com.example.worddictionary.api.parseJsonToStringList
import com.example.worddictionary.api.parseJsonToWord
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao
import kotlinx.coroutines.launch


// The view model uses the database (dao) and the API to
// access data for this fragment.
class SearchViewModel(private val dao: WordDatabaseDao) : ViewModel() {

        // The fragment observes changes to the wordDef, so that when wordDef
        // is populated with a word found using the API it can navigate
        // to the add word fragment
        private val _wordDef = MutableLiveData<Word>()
        val wordDef: LiveData<Word>
            get() = _wordDef

        // The fragment observes changes to the suggestedWords, so that when it
        // is populated with a word found using the API it displays the list
        // of suggestion on the screen.
        private val _suggestedWords = MutableLiveData<List<String>>()
        val suggestedWords: LiveData<List<String>>
            get() = _suggestedWords

        //snackbar for when word is found in dictionary
        private var _showSnackbarEvent = MutableLiveData<Boolean>()
        val showSnackBarEvent: LiveData<Boolean>
            get() = _showSnackbarEvent

        // Search the API for the searchWord
        fun performWordSearch(searchWord: String) {
            if (searchWord.isNotBlank()) {
                viewModelScope.launch {

                    if (!isWordInDictionary(searchWord)) {
                        var response = DictionaryApi.retrofitService.getWord(searchWord)
                        val jsonString = response.body()!!

                        //if word found, return word
                        if (jsonString.startsWith("[{")) {
                            _wordDef.value = parseJsonToWord(searchWord, jsonString)

                        //if word not found, return possibles
                        } else {
                            _suggestedWords.value = parseJsonToStringList(jsonString)
                        }
                    }
                    //if word is in dictionary, notify via snackbar
                    else {
                        _showSnackbarEvent.value = true
                    }
                }
            }
        }

        private suspend fun isWordInDictionary(searchWord: String): Boolean {
            return dao.checkWord(searchWord)
        }

        fun doneShowingSnackbar() {
            _showSnackbarEvent.value = false
        }

        @SuppressLint("NullSafeMutableLiveData")
        // This is needed so that observers (the fragment) can reset the wordDef
        // to prevent re-triggering of the data change event.
        fun resetWordDef() {
            _wordDef.value = null
        }
    }