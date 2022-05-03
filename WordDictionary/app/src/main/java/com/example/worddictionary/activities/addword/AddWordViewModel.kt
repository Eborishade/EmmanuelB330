package com.example.worddictionary.activities.addword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao
import kotlinx.coroutines.launch

class AddWordViewModel(val word: Word, application: Application) : AndroidViewModel(application) {

    fun addWord(dao: WordDatabaseDao) {
        viewModelScope.launch { dao.insert(word) }
    }
}