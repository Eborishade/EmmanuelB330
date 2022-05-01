package com.example.worddictionary.activities.words

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao

class WordDictViewModel(val database: WordDatabaseDao,
                        application: Application) : AndroidViewModel(application) {

    private val _activeWords = database.getActive()
    val activeWords: LiveData<List<Word>>
        get() = _activeWords

    private val _inactiveWords = database.getInactive()
    val inactiveWords: LiveData<List<Word>>
        get() = _inactiveWords

    private val _allWords = database.getAllWords()
    val allWords: LiveData<List<Word>>
        get() = _allWords

    private val adapter = WordDictAdapter()

    init {
        adapter.submitList(allWords.value)
    }

    fun changeFilter(filter: String) {
        val adapter = WordDictAdapter()
        Log.d("Change: DatabaseCheck", "All Words: ${allWords.value}")

        if (filter == "show_active"){adapter.submitList(activeWords.value) }
        if (filter == "show_inactive"){adapter.submitList(inactiveWords.value)}
        else { adapter.submitList(allWords.value) }
    }
}