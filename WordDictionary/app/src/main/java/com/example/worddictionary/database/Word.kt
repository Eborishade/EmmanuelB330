package com.example.worddictionary.Api


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_dictionary_table")

data class Word (
    @PrimaryKey(autoGenerate = false)
    val wordId: String,

    @ColumnInfo(name = "short_definition_1")
    val shortDef1: String,

    @ColumnInfo(name = "short_definition_2")
    val shortDef2: String = "",

    @ColumnInfo(name = "short_definition_3")
    val shortDef3: String = ""
)
