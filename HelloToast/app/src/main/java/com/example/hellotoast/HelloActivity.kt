package com.example.hellotoast

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/*
Activity created for Task 2
 */
class HelloActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_hello)
                val intent = intent
                val message = intent.getStringExtra("Count")
                val textView = findViewById<TextView>(R.id.text_count)
                textView.text = message
                textView.visibility = View.VISIBLE

                findViewById<ImageButton>(R.id.back_button).setOnClickListener {
                        backButton()
                }
        }

        private fun backButton(){
                finish()
        }


}