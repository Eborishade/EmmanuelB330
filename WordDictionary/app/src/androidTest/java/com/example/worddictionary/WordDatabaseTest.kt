package com.example.worddictionary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.trackmysleepquality.getOrAwaitValue
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabase
import com.example.worddictionary.database.WordDatabaseDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Rule


/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class WordDatabaseTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var wordDao: WordDatabaseDao
    private lateinit var db: WordDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context,
            WordDatabase::class.java).allowMainThreadQueries().build()

        wordDao = db.wordDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndCheckWord() = runBlocking {
        // Given
        val word = Word("leader", "the one in front")

        // When
        wordDao.insert(word)

        // Then
        val wordPresent = wordDao.checkWord("leader")
        assertThat(wordPresent, `is`(true))
    }

    @Test
    @Throws(Exception::class)
    fun updateAndGetWord() = runBlocking {
        // Given
        val word = Word("leader", "the one in front")

        // When
        wordDao.insert(word)
        val updater = wordDao.getWord("leader")
        updater.active = false
        wordDao.updateWord(updater)


        // Then
        val activeList = wordDao.getActive().getOrAwaitValue()
        assertThat(activeList.size, `is`(0))
    }

    @Test
    @Throws(Exception::class)
    fun getActiveInactiveAll() = runBlocking {
        // Given
        val word = Word("leader", "the one in front")
        val word2 = Word("middle", "the one in the center")
        val word3 = Word("last", "the one at the end")
        word.active = false

        // When
        wordDao.insert(word)
        wordDao.insert(word2)
        wordDao.insert(word3)

        // Then
        val inactiveList = wordDao.getInactive().getOrAwaitValue()
        val activeList = wordDao.getActive().getOrAwaitValue()
        val allWords = wordDao.getAllWords().getOrAwaitValue()

        assertThat(inactiveList.size, `is`(1))
        assertThat(activeList.size, `is`(2))
        assertThat(allWords.size, `is`(3))

    }


    @Test
    @Throws(Exception::class)
    fun clear() = runBlocking {
        // Given
        val word = Word("leader", "the one in front")

        // When
        wordDao.insert(word)
        wordDao.clear()

        // Then
        val allWords = wordDao.getAllWords().getOrAwaitValue()
        val size = allWords.size
        assertThat(size, `is`(0))
    }

}