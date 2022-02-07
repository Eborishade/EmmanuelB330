package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        /**Broken ** /
        String message = intent.getStringExtra("Text");
        //TextView textView = findViewById(R.id.text_message);
        //textView.setText(message);
        /**End Broken **/


        /** Workaround **/
        int viewer = intent.getIntExtra("val", 0);
        TextView txtview;
        if (viewer == 1){
            txtview = findViewById(R.id.text_message);
        } else if (viewer == 2){
            txtview = findViewById(R.id.text_m2);
        } else {
            txtview = findViewById(R.id.text_m3);
        }
        txtview.setVisibility(View.VISIBLE);
        /** End Workaround **/
    }
}