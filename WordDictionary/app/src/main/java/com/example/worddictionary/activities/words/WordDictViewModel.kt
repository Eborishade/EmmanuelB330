package com.example.worddictionary.activities.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worddictionary.database.Word

class WordDictViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    //see Mars OverviewViewModel
    private val _words = MutableLiveData<List<Word>>()
    val words: LiveData<List<Word>>
        get() = _words

}