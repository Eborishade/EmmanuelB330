package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var diceImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //roll button setup
        diceImage = findViewById(R.id.dice_image)
        val rollButton: Button = findViewById(R.id.roll_button)
        val clearButton: Button = findViewById(R.id.clear_button)
        rollButton.setOnClickListener { rollDice() }
        clearButton.setOnClickListener { clearDice() }


                                                                                                                                                                                                                                                                                                                                  }

        private fun rollDice(){
            val randomInt = (1..6).random()

            //Toast.makeText(this, "button clicked!", Toast.LENGTH_SHORT).show()
            //val resultText: TextView = findViewById(R.id.result_text)
            //resultText.text = randomInt.toString()
            val drawableResource = when (randomInt) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            diceImage.setImageResource(drawableResource)//set to random die
        }

        private fun clearDice(){
            diceImage.setImageResource(R.drawable.empty_dice)//set to random die
        }
}