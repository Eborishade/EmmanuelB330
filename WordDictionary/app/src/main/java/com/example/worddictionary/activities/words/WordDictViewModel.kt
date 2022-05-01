package com.example.worddictionary.activities.words

import android.app.Application
import androidx.lifecycle.*
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao

class WordDictViewModel(val database: WordDatabaseDao,
                        application: Application) : AndroidViewModel(application) {

    private var activeWords = database.getActive()
    private var inactiveWords = database.getInactive()
    private var allWords = database.getAllWords()

    private var _display = allWords
    val display: LiveData<List<Word>>
        get() = _display


    fun changeFilter(filter: String) {
        _display = when (filter) {
            "show_active" -> { activeWords }
            "show_inactive" -> { inactiveWords }
            else -> { allWords }
        }
    }
}