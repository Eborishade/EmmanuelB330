package com.example.worddictionary.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WordDatabaseDao {

    @Insert
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Query ("SELECT * from word_dictionary_table WHERE wordId = :key")
    suspend fun get(key: Long): Word?

    @Query("DELETE FROM word_dictionary_table")
    suspend fun clear()

    @Query("SELECT * FROM word_dictionary_table ORDER BY wordId DESC LIMIT 1")
    suspend fun getWord(): Word?

    @Query("SELECT * FROM word_dictionary_table ORDER BY wordId DESC")
    fun getAllWords(): LiveData<List<Word>>

}
