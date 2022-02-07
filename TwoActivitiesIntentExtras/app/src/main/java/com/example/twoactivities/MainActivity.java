package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    private TextView text1, text2, text3;
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text_one);
        text2 = findViewById(R.id.text_two);
        text3 = findViewById(R.id.text_three);
        message = text1.getText().toString();

    }

    //launched from activity_main button:on click
    public void launchTextOne(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        message = text1.getText().toString();

        intent.putExtra("Text", message);
        intent.putExtra("val", 1);
    }

    public void launchTextTwo(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        message = text2.getText().toString();

        intent.putExtra("Text", message);
        intent.putExtra("val", 2);
    }

    public void launchTextThree(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        message = text3.getText().toString();

        intent.putExtra("Text", message);
        intent.putExtra("val", 3);
    }



}