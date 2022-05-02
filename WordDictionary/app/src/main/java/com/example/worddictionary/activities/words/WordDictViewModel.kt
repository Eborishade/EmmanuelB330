package com.example.worddictionary.activities.words

import android.app.Application
import androidx.lifecycle.*
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao
import kotlinx.coroutines.launch

class WordDictViewModel(val database: WordDatabaseDao,
                        application: Application) : AndroidViewModel(application) {

    private var _activeWords = database.getActive()
    val activeWords: LiveData<List<Word>>
        get() = _activeWords

    private var _inactiveWords = database.getInactive()
    val inactiveWords: LiveData<List<Word>>
        get() = _inactiveWords

    private var _allWords = database.getAllWords()
    val allWords: LiveData<List<Word>>
        get() = _allWords

    private var _filter = MutableLiveData<String>()
    val filter: LiveData<String>
        get() = _filter


    fun changeFilter(fltr: String) {
        _filter.value = fltr
    }

    fun clearData(){
        viewModelScope.launch {
            database.clear()
        }
    }

    fun toggleActive(word: Word) {
        viewModelScope.launch {
            word.active = !word.active
            database.updateWord(word)
        }
    }
}