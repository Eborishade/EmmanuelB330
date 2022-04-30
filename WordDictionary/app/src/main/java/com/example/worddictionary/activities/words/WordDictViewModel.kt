package com.example.worddictionary.activities.words

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Query
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao
import kotlinx.coroutines.launch

class WordDictViewModel(val database: WordDatabaseDao,
                        application: Application) : AndroidViewModel(application) {

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    /* Handled by Search or other
    suspend fun insert(word: Word) {
        database.insert(word)
    }

    suspend fun update(word: Word) {
        database.updateWord(word)
    }

    suspend fun checkWord(key: String): Boolean {
        return database.checkWord(key)
    }

    suspend fun clear() {
        database.clear()
    }
    */
//on swipe, set active. on menu click, reset to display active/inactive
    suspend fun getWord(key: String): Word {
        return database.getWord(key)
    }

    suspend fun getActive(): LiveData<List<Word>>{
        return database.getActive()
    }

    suspend fun getInactive(): LiveData<List<Word>>{
        return database.getInactive()
    }

    suspend fun getAllWords(): LiveData<List<Word>>{
        return database.getAllWords()
    }

}