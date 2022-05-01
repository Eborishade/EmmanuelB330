package com.example.worddictionary.activities.addword

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.worddictionary.database.Word

class AddWordViewModelFactory
    (
    private val word: Word,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddWordViewModel::class.java)) {
            return AddWordViewModel(word, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}