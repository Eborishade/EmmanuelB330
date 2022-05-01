package com.example.worddictionary.activities.addword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabase
import kotlinx.coroutines.launch

class AddWordViewModel(word: Word, application: Application) : AndroidViewModel(application) {
    val word = word
    val dao = WordDatabase.getInstance(application).wordDatabaseDao

    fun addWord(){
        viewModelScope.launch { dao.insert(word) }
    }
}