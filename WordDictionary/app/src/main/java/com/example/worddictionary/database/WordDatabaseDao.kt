package com.example.worddictionary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface WordDatabaseDao {

    @Insert
    suspend fun insert(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Query("select exists(SELECT * from word_dictionary_table WHERE wordId = :key)")
    suspend fun checkWord(key: String): Boolean

    @Query("SELECT * from word_dictionary_table WHERE wordId = :key")
    suspend fun getWord(key: String): Word

    @Query("DELETE FROM word_dictionary_table")
    suspend fun clear()

    @Query("SELECT * from word_dictionary_table WHERE active = 1 ORDER BY wordId DESC")
    fun getActive(): LiveData<List<Word>>

    @Query("SELECT * FROM word_dictionary_table WHERE active = 0 ORDER BY wordId DESC")
    fun getInactive(): LiveData<List<Word>>

    @Query("SELECT * FROM word_dictionary_table ORDER BY wordId DESC")
    fun getAllWords(): LiveData<List<Word>>

}
