package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

/*
 * This activity allows the user to roll a die and view the result on the screen.
 */
class MainActivity : AppCompatActivity() {
    private var toastie: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)

        rollButton.setOnClickListener { rollDie(true) }

        rollDie()
    }

    /*
     * Roll the die to update the screen with the result.
     */
    private fun rollDie(showToast: Boolean = false) {
        // Create a new Dice object with 6 sides
        val myDie = Dice(6)
        val dieRoll = myDie.roll()
        // val resultTextView: TextView = findViewById(R.id.textView)
        val dieImage: ImageView = findViewById(R.id.imageView)

        // Update the screen with the rolled number
        // resultTextView.text = dieRoll.toString()
        val drawableResource = when (dieRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        dieImage.setImageResource(drawableResource)
        dieImage.contentDescription = dieRoll.toString()

        if (showToast) {
            toastie?.cancel()

            toastie = Toast.makeText(
                this,
                "${dieRoll} rolled!",
                Toast.LENGTH_SHORT
            )

            toastie?.show()
        }
    }
}

class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}