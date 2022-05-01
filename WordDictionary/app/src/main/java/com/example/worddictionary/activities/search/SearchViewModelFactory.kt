package com.example.worddictionary.activities.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.worddictionary.database.WordDatabaseDao

class SearchViewModelFactory(private val dao: WordDatabaseDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}