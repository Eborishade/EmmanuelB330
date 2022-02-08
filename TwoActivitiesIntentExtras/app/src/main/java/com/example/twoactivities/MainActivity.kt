package com.example.twoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var text1: TextView? = null
    private var text2: TextView? = null
    private var text3: TextView? = null
    private var message: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text1 = findViewById(R.id.text_one)
        text2 = findViewById(R.id.text_two)
        text3 = findViewById(R.id.text_three)
        message = text1?.getText().toString()
    }

    //launched from activity_main button:on click
    fun launchTextOne(view: View?) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        message = text1!!.text.toString()
        intent.putExtra("Text", message)
        startActivity(intent)
    }

    fun launchTextTwo(view: View?) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        message = text2!!.text.toString()
        intent.putExtra("Text", message)
        startActivity(intent)
    }

    fun launchTextThree(view: View?) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        message = text3!!.text.toString()
        intent.putExtra("Text", message)
        startActivity(intent)
    }

    companion object {
        private val LOG_TAG = MainActivity::class.java.simpleName
    }
}