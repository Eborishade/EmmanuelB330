package com.example.twoactivities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent = intent
        val message = intent.getStringExtra("Text")
        val textView = findViewById<TextView>(R.id.text_message)
        textView.text = message
        textView.visibility = View.VISIBLE
    }
}