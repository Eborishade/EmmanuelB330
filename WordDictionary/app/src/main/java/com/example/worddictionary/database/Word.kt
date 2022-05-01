package com.example.worddictionary.database


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "word_dictionary_table")
@Parcelize
data class Word (
    @PrimaryKey(autoGenerate = false)
    val wordId: String,
    val shortDef1: String,
    val shortDef2: String = "",
    val shortDef3: String = "",
    val image: String? = null,
    var active: Boolean = true
) : Parcelable
