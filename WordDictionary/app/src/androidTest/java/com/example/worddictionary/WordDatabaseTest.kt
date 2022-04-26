package com.example.worddictionary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
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
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context,
            WordDatabase::class.java).allowMainThreadQueries().build()
        // Allowing main thread queries, just for testing.
        wordDao = db.wordDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() = runBlocking {
        // Given
        val word = Word("leader", "the one in front")

        // When
        wordDao.insert(word)

        // Then
        val thisWord = wordDao.getWord()
        assertThat(thisWord?.wordId, `is`("leader"))
    }

    /*
    @Test
    @Throws(Exception::class)
    fun getNightById() = runBlocking {
        // Given
        val night1 = SleepNight(sleepQuality = 8)

        // When
        sleepDao.insert(night1)

        // Then
        val tonight = sleepDao.get(1L)
        assertThat(tonight?.sleepQuality, `is`(8))
    }

    @Test
    @Throws(Exception::class)
    fun getTonight() = runBlocking {
        // Given
        val night2 = SleepNight(sleepQuality = 7)

        // When
        sleepDao.insert(night2)

        // Then
        val tonight = sleepDao.getTonight()
        assertThat(tonight?.sleepQuality, `is`(7))
    }

    @Test
    @Throws(Exception::class)
    fun updateNight() = runBlocking {
        // Given
        val night3 = SleepNight(sleepQuality = 6)

        // When
        sleepDao.insert(night3)
        val updater = sleepDao.getTonight()
        updater?.sleepQuality = 5
        sleepDao.update(updater!!)

        // Then
        val tonight = sleepDao.getTonight()
        assertThat(tonight?.sleepQuality, `is`(5))
    }

    @Test
    @Throws(Exception::class)
    fun clear() = runBlocking {
        // Given
        val night4 = SleepNight(sleepQuality = 4)

        // When
        sleepDao.insert(night4)
        sleepDao.clear()

        // Then
        val allNights = sleepDao.getAllNights().getOrAwaitValue()
        val size = allNights.size
        assertThat(size, `is`(0))
    }

    @Test
    @Throws(Exception::class)
    fun getAllNights() = runBlocking {
        // Given
        val night = SleepNight(sleepQuality = 9)
        val night1 = SleepNight(sleepQuality = 8)
        val night2 = SleepNight(sleepQuality = 7)
        val night3 = SleepNight(sleepQuality = 6)

        //val nightList = listOf(night, night1, night2, night3)
        // When
        sleepDao.insert(night)
        sleepDao.insert(night1)
        sleepDao.insert(night2)
        sleepDao.insert(night3)

        // Then
        val allNights = sleepDao.getAllNights().getOrAwaitValue()
        val size = allNights.size
        assertThat(size, `is`(4))
    }
*/
}