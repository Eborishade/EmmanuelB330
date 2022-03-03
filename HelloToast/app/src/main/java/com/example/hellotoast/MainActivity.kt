package com.example.hellotoast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

class MainActivity : AppCompatActivity() {
    private lateinit var mShowCount: TextView
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mShowCount = findViewById(R.id.show_count)
        //Task 1: Count Button
        findViewById<TextView>(R.id.button_count).setOnClickListener {
            countUp()
        }

        //Task 2: Toast Button
        findViewById<TextView>(R.id.button_toast).setOnClickListener {
            launchHelloActivity(it)
        }

        //Task 3: Rotation Preservation = ViewModel addition
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //set text to viewModel on launch
        mShowCount.text = viewModel.count.value!!.toString()
    }

    private fun showToast() {
        val toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT)
        toast.show()
    }

    //Modified Task 3
    private fun countUp() {
        viewModel.countUp()
        mShowCount.text = viewModel.count.value!!.toString()
    }

    //Task 2
    fun launchHelloActivity(view: View?){
        val intent = Intent(this, HelloActivity::class.java)
        intent.putExtra("Count", viewModel.count.value!!.toString())
        startActivity(intent)
    }
}