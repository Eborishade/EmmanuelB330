package com.example.worddictionary.api

import com.example.worddictionary.database.Word
import org.json.JSONArray

fun parseJsonToWord(wordId: String, jsonStr: String): Word {
    val json = JSONArray(jsonStr)
    val entry = json.getJSONObject(0)
    val shortDef = entry.getJSONArray("shortdef")
    var image: String? = null


    if (entry.has("art")) {
        image = entry.getJSONObject("art").getString("artid")
    }

    val word : Word = when (shortDef.length()){
        0 -> Word(wordId, "No definition available")
        1 -> Word(wordId, shortDef.getString(0), image = image)
        2 -> Word(wordId, shortDef.getString(0), shortDef.getString(1), image = image)
        else -> Word(
            wordId, shortDef.getString(0),
            shortDef.getString(1),
            shortDef.getString(2),
            image = image
        )
    }
    return word

}

fun parseJsonToStringList(jsonStr: String) : List<String> {
        val json = JSONArray(jsonStr)
        val wordList = mutableListOf<String>()
        for (i in 0 until json.length()) {
            wordList.add(i, json.getString(i))
        }
        return wordList
}