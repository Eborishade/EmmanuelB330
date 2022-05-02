package com.example.worddictionary.activities.addword

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao
import kotlinx.coroutines.launch

class AddWordViewModel(word: Word, application: Application) : AndroidViewModel(application) {
    val word = word


    fun addWord(dao: WordDatabaseDao){
        viewModelScope.launch { dao.insert(word) }
    }
}