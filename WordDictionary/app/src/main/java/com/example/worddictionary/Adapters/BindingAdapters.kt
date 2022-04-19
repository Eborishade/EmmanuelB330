package com.example.worddictionary.Adapters

import android.widget.ImageView
import androidx.core.net.toUri
import com.example.worddictionary.database.Word
import org.json.JSONArray

//@BindingAdapters("parseJson")
fun parseJsonToWord(wordId: String, jsonStr: String): Word {
    val json = JSONArray(jsonStr)
    val entry = json.getJSONObject(0)
    val shortDef = entry.getJSONArray("shortdef")

/*    val word : Word = when (shortDef.length()){
        0 -> Word(wordId, "No definition available")
        0 -> Word(wordId, "No definition available")
        0 -> Word(wordId, "No definition available")
        else -> Word(
            wordId, shortDef.getString(0),
            shortDef.getString(1),
            shortDef.getString(2)
        )
    }
    return word
    
 */
    return Word("test", "1", "1", "1")
}